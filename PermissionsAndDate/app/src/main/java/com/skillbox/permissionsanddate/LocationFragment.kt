package com.skillbox.permissionsanddate

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.location.LocationServices
import com.skillbox.permissionsanddate.databinding.FragmentLocationBinding
import com.skillbox.permissionsanddate.extentions.autoCleared
import com.skillbox.permissionsanddate.extentions.withArguments
import jp.wasabeef.recyclerview.animators.SlideInRightAnimator
import org.threeten.bp.Instant
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneId
import kotlin.random.Random

class LocationFragment : Fragment() {

    private var rationaleDialog: AlertDialog? = null
    private lateinit var binding: FragmentLocationBinding
    private var myAdapter: Adapter by autoCleared()
    private var selectedInstant: Instant? = null
    private var locations = arrayListOf<DataSet>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentLocationBinding.inflate(inflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        locationPermissionCheck()
    }

    private fun locationPermissionCheck() {
        val isLocationPermissionGranted = ActivityCompat.checkSelfPermission(
            requireContext(), Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
        if (isLocationPermissionGranted) {
            locationPermissionGranted()
        } else {
            binding.tV1.visibility = View.VISIBLE
            binding.b1.setOnClickListener {
                val needRationale = ActivityCompat.shouldShowRequestPermissionRationale(
                        requireActivity(),
                        Manifest.permission.ACCESS_FINE_LOCATION
                )
                if (needRationale) {
                    showLocationRationaleDialog()
                } else {
                    requestLocationPermission()
                }
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
            locationPermissionGranted()
        } else {
            Toast.makeText(context, "Невозможно получить доступ к локации без разрешения", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        rationaleDialog?.dismiss()
        rationaleDialog = null
    }

    private fun showLocationRationaleDialog() {
        rationaleDialog = AlertDialog.Builder(requireContext())
                .setMessage("Разрешение для отображения локации")
                .setPositiveButton("Мне понятно") { _, _ -> requestLocationPermission() }
                .setNegativeButton("Отмена", null)
                .show()
    }

    private fun locationPermissionGranted() {
        initList()
        binding.tV1.visibility = View.GONE
        binding.tV2.visibility = View.VISIBLE
        binding.b1.visibility = View.GONE
        binding.b2.visibility = View.VISIBLE
        binding.b2.setOnClickListener {
            addLocationInfo()
        }
    }

    @SuppressLint("MissingPermission")
    private fun addLocationInfo() {
        LocationServices.getFusedLocationProviderClient(requireContext())
                .lastLocation
                .addOnSuccessListener { it?.let{
                    val newLocation = DataSet.DataSetWithLocation(
                            Random.nextLong(),
                            Instant.now(),
                            it.latitude.toString(),
                            it.longitude.toString(),
                            it.altitude.toString(),
                            ""
                    )
                    locations = (listOf(newLocation) + locations) as ArrayList<DataSet>
                } ?: Toast.makeText(context, "Локация отсутствует" , Toast.LENGTH_SHORT).show()}
                .addOnCanceledListener { Toast.makeText(context, "Запрос отменён" , Toast.LENGTH_SHORT).show() }
                .addOnFailureListener { Toast.makeText(context, "Запрос завершился неудачей" , Toast.LENGTH_SHORT).show() }
        if (locations.isNotEmpty()) {
            binding.rV.visibility = View.VISIBLE
            binding.tV2.visibility = View.GONE
        }
        myAdapter.items = locations
        binding.rV.scrollToPosition(0)
    }


    private fun requestLocationPermission() {
        requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), PERMISSION_REQUEST_CODE)
    }

    private fun initList() {
        myAdapter = Adapter { position -> changeTime(position) }
        with(binding.rV) {
            adapter = myAdapter
            layoutManager = LinearLayoutManager(requireContext()).apply {
                orientation = RecyclerView.VERTICAL
            }
            setHasFixedSize(true)
            val dividerItemDecoration = DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
            )
            addItemDecoration(dividerItemDecoration)
            addItemDecoration(ItemOffsetDecoration(requireContext()))
            itemAnimator = SlideInRightAnimator()
        }
        myAdapter.items = locations
    }

    private fun changeTime(position: Int) {
        val item = locations[position] as DataSet.DataSetWithLocation
        val itemInstant = item.createdAt
        val currentDateTime = LocalDateTime.ofInstant(itemInstant, ZoneId.systemDefault())
        DatePickerDialog(
                requireContext(),
                { _, year, month, dayOfMonth ->
                    TimePickerDialog(
                            requireContext(),
                            { _, hourOfDay, minute ->
                                val zoneDateTime = LocalDateTime.of(year, month + 1, dayOfMonth, hourOfDay, minute)
                                        .atZone(ZoneId.systemDefault())
                                Toast.makeText(context, "Время выбрано", Toast.LENGTH_SHORT).show()
                                selectedInstant = zoneDateTime.toInstant()
                                val newItem = item.copy(createdAt = selectedInstant ?: itemInstant)
                                locations[position] = newItem
                                selectedInstant = null
                                myAdapter.items = locations
                                binding.rV.scrollToPosition(position)
                            },
                            currentDateTime.hour,
                            currentDateTime.minute,
                            true
                    ).show()
                },
                currentDateTime.year,
                currentDateTime.month.value - 1,
                currentDateTime.dayOfMonth)
                .show()
    }

    companion object {
        private const val PERMISSION_REQUEST_CODE = 1
        fun newInstance(): LocationFragment {
            return LocationFragment().withArguments {
            }
        }
    }
}
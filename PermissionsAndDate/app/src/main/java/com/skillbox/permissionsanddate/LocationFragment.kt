package com.skillbox.permissionsanddate

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.net.Uri
import android.os.Bundle
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.location.*
import com.google.android.gms.location.LocationServices.getFusedLocationProviderClient
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
    private var locations = mutableListOf<DataSet>()
    private var dialog: AlertDialog? = null

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentLocationBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val isGooglePlayServicesAvailable = GoogleApiAvailability
                .getInstance()
                .isGooglePlayServicesAvailable(requireContext())
        Toast.makeText(context, "Сервисы $isGooglePlayServicesAvailable", Toast.LENGTH_SHORT).show()
        if (isGooglePlayServicesAvailable == 1) {
            errorDialog()
        } else if (isGooglePlayServicesAvailable == 2) {
            updateDialog()
        }
        locationPermissionCheck()
    }

    private fun errorDialog() {
        dialog = AlertDialog.Builder(requireContext())
                .setTitle("Ошибка")
                .setMessage("На вашем устройстве отсутствуют GooglePlayServices")
                .setCancelable(false)
                .create()
        dialog?.show()
    }

    private fun updateDialog() {
        dialog = AlertDialog.Builder(requireContext())
                .setTitle("Ошибка")
                .setMessage("GooglePlayServices устарели")
                .setPositiveButton("Обновить!") { _, _ ->
                    try {
                        startActivity(Intent(Intent.ACTION_VIEW,
                                Uri.parse("market://details?id=com.google.android.gms")))
                    } catch (e: ActivityNotFoundException) {
                        startActivity(Intent(Intent.ACTION_VIEW,
                                Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.gms")))
                    }
                }
                .setCancelable(false)
                .create()
        dialog?.show()
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
        dialog?.dismiss()
        dialog = null
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

    @SuppressLint("MissingPermission")
    private fun locationPermissionGranted() {
        initList()
        binding.tV1.visibility = View.GONE
        binding.tV2.visibility = View.VISIBLE
        binding.b1.visibility = View.GONE
        binding.b2.visibility = View.VISIBLE

        binding.b2.setOnClickListener {
            addLocationInfo()
//            startLocationUpdates()  //Старт автоматического добавления локации в список.
        }
    }

    @SuppressLint("MissingPermission")
    private fun addLocationInfo() {
        getFusedLocationProviderClient(requireContext())
                .lastLocation
                .addOnSuccessListener {
                    it?.let {
                        val newLocation = DataSet(
                                Random.nextLong(),
                                Instant.now(),
                                it.latitude.toString(),
                                it.longitude.toString(),
                                it.altitude.toString(),
                                ""
                        )
                        locations = (locations + mutableListOf(newLocation)).toMutableList()
                        isNotEmpty()
                        myAdapter.items = locations
                    } ?: Toast.makeText(context, "Локация отсутствует", Toast.LENGTH_SHORT).show()
                }
                .addOnCanceledListener { Toast.makeText(context, "Запрос отменён", Toast.LENGTH_SHORT).show() }
                .addOnFailureListener { Toast.makeText(context, "Запрос завершился неудачей", Toast.LENGTH_SHORT).show() }
    }

    private fun isNotEmpty() {
        if (locations.isNotEmpty()) {
            binding.rV.visibility = View.VISIBLE
            binding.tV2.visibility = View.GONE
        }
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
            itemAnimator = SlideInRightAnimator()
        }
    }

    private fun changeTime(position: Int) {
        Toast.makeText(context, "Нажал на позицию $position", Toast.LENGTH_SHORT).show()
        val item = locations[position]
        val itemInstant = item.createdAt
        locations.removeAt(position)
        myAdapter.items = locations
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
                                val newItem = item.copy(id = Random.nextLong(), createdAt = selectedInstant
                                        ?: itemInstant)
                                locations.add(position, newItem)
                                selectedInstant = null
                                myAdapter.items = locations
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

    @SuppressLint("MissingPermission")
    private fun startLocationUpdates() {
        binding.b2.visibility = View.GONE
        val mLocationRequest = LocationRequest()
        mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        mLocationRequest.interval = 5000
        mLocationRequest.fastestInterval = 1000

        val builder = LocationSettingsRequest.Builder()
        builder.addLocationRequest(mLocationRequest)
        val locationSettingsRequest = builder.build()

        val settingsClient = LocationServices.getSettingsClient(requireActivity())
        settingsClient.checkLocationSettings(locationSettingsRequest)


        getFusedLocationProviderClient(requireActivity()).requestLocationUpdates(mLocationRequest, object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                onLocationChanged(locationResult.lastLocation)
            }
        },
                Looper.myLooper())
    }

    fun onLocationChanged(location: Location) {
        val newLocation = DataSet(
                Random.nextLong(),
                Instant.now(),
                location.latitude.toString(),
                location.longitude.toString(),
                location.altitude.toString(),
                ""
        )
        locations = (mutableListOf(newLocation) + locations).toMutableList()
        isNotEmpty()
        myAdapter.items = locations
    }

    companion object {
        private const val PERMISSION_REQUEST_CODE = 1
        fun newInstance(): LocationFragment {
            return LocationFragment().withArguments {
            }
        }
    }
}
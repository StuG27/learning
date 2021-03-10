package com.skillbox.viewmodelandnavigation.data

import kotlin.random.Random


object PersonRepository {

    var persons = arrayListOf(
            Person.Actor(
                    1,
                    "Том Хэнкс",
                    "https://avatars.mds.yandex.net/get-kinopoisk-image/1773646/3acd328c-721a-47ac-a7bf-fe7d5efb69fc/280x420",
                    64,
                    true
            ),
            Person.Producer(
                    2,
                    "Питер Джексон",
                    "https://avatars.mds.yandex.net/get-kinopoisk-image/1777765/489b2e6a-555c-40c2-82c3-942adee761e8/280x420",
                    59,
                    true,
                    "Властелин колец: Возвращение Короля"
            ),
            Person.Actor(
                    3,
                    "Джонни Депп",
                    "https://avatars.mds.yandex.net/get-kinopoisk-image/1600647/bcaecab2-5007-40d4-82e5-38cb54f24454/280x420",
                    57,
                    false
            ),
            Person.Actor(
                    4,
                    "Том Круз",
                    "https://avatars.mds.yandex.net/get-kinopoisk-image/1946459/2eb2fc4d-a8bd-43b0-83cd-35feacb8ccae/280x420",
                    58,
                    false
            ),
            Person.Producer(
                    5,
                    "Гильермо дель Торо",
                    "https://avatars.mds.yandex.net/get-kinopoisk-image/1773646/66eab548-dafa-4119-8f04-0a59d08ff4e5/280x420",
                    56,
                    true,
                    "Лабиринт Фавна"
            ),
            Person.Actor(
                    6,
                    "Шэрон Стоун",
                    "https://avatars.mds.yandex.net/get-kinopoisk-image/1777765/8cb776b6-1ba1-4307-a003-3f4706eb1b80/280x420",
                    62,
                    false
            ),
            Person.Actor(
                    7,
                    "Хоакин Феникс",
                    "https://avatars.mds.yandex.net/get-kinopoisk-image/1946459/85880973-3516-4ad5-91ae-c9ead4d10f77/280x420",
                    46,
                    true
            ),
            Person.Producer(
                    8,
                    "Кристофер Нолан",
                    "https://avatars.mds.yandex.net/get-kinopoisk-image/1777765/2aeee5fd-2f79-4897-a1d2-eec29f666e35/280x420",
                    50,
                    false,
                    "Начало"
            ),
            Person.Producer(
                    9,
                    "Квентин Тарантино",
                    "https://avatars.mds.yandex.net/get-kinopoisk-image/1773646/c5eef897-dfb2-42a3-bc17-d5346f5dc587/280x420",
                    57,
                    false,
                    "Криминальное чтиво"
            ),
            Person.Actor(
                    10,
                    "Леонардо ДиКаприо",
                    "https://avatars.mds.yandex.net/get-kinopoisk-image/1600647/9c33caef-be28-4257-b7ed-a407698f1a32/280x420",
                    46,
                    true
            ),
            Person.Actor(
                    11,
                    "Антонио Бандерас",
                    "https://avatars.mds.yandex.net/get-kinopoisk-image/1773646/52baaa66-5da3-41cc-b0a0-a772716b8804/280x420",
                    60,
                    false
            ),
            Person.Producer(
                    12,
                    "Ридли Скотт",
                    "https://avatars.mds.yandex.net/get-kinopoisk-image/1773646/3dc522eb-e149-4ff3-ab4c-45d9e4ebda05/280x420",
                    83,
                    false,
                    "Гладиатор"
            ),
            Person.Actor(
                    13,
                    "Анджелина Джоли",
                    "https://avatars.mds.yandex.net/get-kinopoisk-image/1773646/d91cf99a-63a2-432a-a72b-ae0b8c6ea1d6/280x420",
                    45,
                    true
            ),
            Person.Actor(
                    14,
                    "Ева Грин",
                    "https://avatars.mds.yandex.net/get-kinopoisk-image/1600647/8e817261-9521-426e-a51f-57a8ce6a92d3/280x420",
                    40,
                    false
            )
    )


    fun createPerson(): Person {
        return persons.random().let {
            when (it) {
                is Person.Actor -> it.copy(Random.nextLong())
                is Person.Producer -> it.copy(Random.nextLong())
            }
        }
    }

    fun deletePerson(persons: List<Person>, position: Int): List<Person> {
        return persons.filterIndexed { index, _ -> index != position } as ArrayList<Person>
    }

}
package com.arangoa.domain.databasemanager.entities

data class WordEntityRemote(val word: String,
                            val letter: String,
                            val image: String,
                            val video: String)

data class ResultsEntity(val results: List<WordEntityRemote>)
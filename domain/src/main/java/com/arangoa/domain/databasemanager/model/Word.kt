package com.arangoa.domain.databasemanager.model

class Word(val word: String,
           val letter: Char,
           val image: String,
           val video: String) {
    companion object {
        val empty = Word("",'a',"","")
    }
}
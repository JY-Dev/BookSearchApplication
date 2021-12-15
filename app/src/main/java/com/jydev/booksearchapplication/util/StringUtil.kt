package com.jydev.booksearchapplication.util

fun String.hasNotOperator() : Boolean =
    contains("-")

fun String.hasOrOperator() : Boolean =
    contains("|")
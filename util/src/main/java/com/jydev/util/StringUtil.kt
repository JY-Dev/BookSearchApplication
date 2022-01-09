package com.jydev.util

fun String.hasNotOperator() : Boolean =
    contains("-")

fun String.hasOrOperator() : Boolean =
    contains("|")
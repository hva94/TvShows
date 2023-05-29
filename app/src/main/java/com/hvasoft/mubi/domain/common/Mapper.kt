package com.hvasoft.mubi.domain.common

/**
 * Interface to map some class from one type to another type
 * Where I means INPUT, the class base
 * Where O means OUTPUT, the class to be mapped
 */

interface Mapper<I, O> {
    fun map(input: I): O
}
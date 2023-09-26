package com.ardidong.domain.common.mapper

interface ResponseToModel<R,M> {
    fun toModel(response: R): M
}
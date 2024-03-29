package com.noobdev.propermvvmarcitecture.data.remote.dto

import com.noobdev.propermvvmarcitecture.domain.model.DataItem
import com.noobdev.propermvvmarcitecture.domain.model.DomainResponse
import com.noobdev.propermvvmarcitecture.domain.utils.DomainMapper
import java.util.*

/**
 * here the entity or dto objects are converted to domain model and vise-versa.
 * */

class ResponseDtoMapper : DomainMapper<ResponseDto, DomainResponse> {
    override fun mapToDomainModel(entity: ResponseDto): DomainResponse {
        return DomainResponse(
            timestamp = entity.timestamp?.let { Date(it) },
            data = entity.data?.let { mapFromEntityList(it) }
        )
    }

    override fun mapFromDomainModel(domainModel: DomainResponse): ResponseDto {
        return ResponseDto(
            timestamp = domainModel.timestamp?.time,
            data = domainModel.data?.let { mapToEntityList(it) }
        )
    }

    private fun mapFromEntityList(input: List<SerializedDataItem>): List<DataItem> {
        val output: MutableList<DataItem> = mutableListOf()
        input.forEach { item ->
            output.add(
                DataItem(
                    priceUsd = item.priceUsd,
                    vwap24Hr = item.vwap24Hr,
                    changePercent24Hr = item.changePercent24Hr,
                    name = item.name,
                    id = item.id,
                )
            )
        }
        return output.toList()
    }

    private fun mapToEntityList(input: List<DataItem>): List<SerializedDataItem> {
        val output: MutableList<SerializedDataItem> = mutableListOf()
        input.forEach { dataItem ->
            output.add(
                SerializedDataItem(
                    priceUsd = dataItem.priceUsd,
                    vwap24Hr = dataItem.vwap24Hr,
                    changePercent24Hr = dataItem.changePercent24Hr,
                    name = dataItem.name,
                    id = dataItem.id
                )
            )
        }
//        return iterator.map { mapToEntity(it).data }
        return output.toList()
    }
}




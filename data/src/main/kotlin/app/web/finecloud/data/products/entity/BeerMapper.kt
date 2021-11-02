package app.web.finecloud.data.products.entity

import app.web.finecloud.domain.base.mapper.Mapper
import app.web.finecloud.domain.products.entity.Beer

class BeerMapper : Mapper<BeerResponse, Beer> {

    override fun mapLeftToRight(obj: BeerResponse): Beer = with(obj) {
        Beer(
            id = id,
            name = name,
            tagline = tagline,
            description = description,
            imageUrl = imageUrl,
            abv = abv
        )
    }

}
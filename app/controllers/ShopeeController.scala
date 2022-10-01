package controllers

import dao.ShopeeDBRepository
import models.OrderDetail
import org.joda.time.DateTime
import play.api.libs.functional.syntax._
import play.api.libs.json._
import play.api.mvc._

import javax.inject._

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class ShopeeController @Inject()(cc: ControllerComponents, shopeeDBRepository: ShopeeDBRepository) (implicit assetsFinder: AssetsFinder)
  extends AbstractController(cc) {

  def getOrderDetail(shop_id: Long, from: Long, to: Long): Action[AnyContent] = Action {

    val orderDetails = shopeeDBRepository.getByShopAndUpdateTime(shop_id, from, to).get
    val jsonBody =
      Json.obj(
        "request" -> Json.obj(
          "params" -> Json.obj(
            "shop_id" -> shop_id,
            "from" -> from,
            "to" -> to
          )
        ),
        "next_cursor" -> "",
        "data" -> Json.arr(
          orderDetails.map(Json.toJson(_)(OrderDetail.writeToJson))
        )
      )
    Ok(jsonBody)
  }

}
package models
import org.joda.time.DateTime
import play.api.libs.json.{JsPath, Writes}
import play.api.libs.functional.syntax._

import scala.annotation.tailrec

case class OrderDetail(
                        order_sn: String,
                        order_status: String,
                        shop_id: Long,
                        order_create_time: Long,
                        order_update_time: Long,
                        buyer_id: Long,
                        order_region: String,
                        currency: String,
                        order_pay_time: Option[Long],
                        order_cancel_by: Option[String],
                        order_cancel_reason: Option[String],
                        order_total_amount: Double,
                        item_id: Long,
                        item_name: String,
                        item_sku: Option[String],
                        quantity: Long,
                        unit_original_price: Double,
                        unit_discounted_price: Double,
                        promotion_id: Option[Long],
                        promotion_type: Option[String],
                        unit_weight: Double,
                        image_url: Option[String]
)

object OrderDetail{
  def writeToJson: Writes[OrderDetail] = {
    ((JsPath \ "order_sn").write[String] and
        (JsPath \ "order_status").write[String] and
        (JsPath \ "shop_id").write[Long] and
        (JsPath \ "order_create_time").write[Long] and
        (JsPath \ "order_update_time").write[Long] and
        (JsPath \ "buyer_id").write[Long] and
        (JsPath \ "order_region").write[String] and
        (JsPath \ "currency").write[String] and
        (JsPath \ "order_pay_time").writeNullable[Long] and
        (JsPath \ "order_cancel_by").writeNullable[String] and
        (JsPath \ "order_cancel_reason").writeNullable[String] and
        (JsPath \ "order_total_amount").write[Double] and
        (JsPath \ "item_id").write[Long] and
        (JsPath \ "item_name").write[String] and
        (JsPath \ "item_sku").writeNullable[String] and
        (JsPath \ "quantity").write[Long] and
        (JsPath \ "unit_original_price").write[Double] and
        (JsPath \ "unit_discounted_price").write[Double] and
        (JsPath \ "promotion_id").writeNullable[Long] and
        (JsPath \ "promotion_type").writeNullable[String] and
        (JsPath \ "unit_weight").write[Double] and
        (JsPath \ "image_url").writeNullable[String]
      ) (unlift(OrderDetail.unapply))

}
}


package dao

import models.OrderDetail
import scalikejdbc.config.DBs
import scalikejdbc.{AutoSession, SQL, WrappedResultSet}

import scala.util.Try

class ShopeeDBRepository{

  DBs.setupAll()
  implicit val session: AutoSession.type = AutoSession
  
  private def convertToEntity(rs: WrappedResultSet): OrderDetail = {
    OrderDetail(
      order_sn = rs.string("order_sn"), 
      order_status = rs.string("order_status"),
      shop_id = rs.long("shop_id"),
      order_create_time = rs.long("order_create_time"),
      order_update_time = rs.long("order_update_time"),
      buyer_id =  rs.long("buyer_id"),
      order_region = rs.string("order_region"),
      currency = rs.string("currency"),
      order_pay_time = rs.longOpt("order_pay_time"),
      order_cancel_by = rs.stringOpt("order_cancel_by"),
      order_cancel_reason = rs.stringOpt("order_cancel_reason"),
      order_total_amount = rs.double("order_total_amount"),
      item_id = rs.long("item_id"),
      item_name = rs.string("item_name"),
      item_sku = rs.stringOpt("item_sku"),
      quantity = rs.long("quantity"),
      unit_original_price = rs.double("unit_original_price"),
      unit_discounted_price = rs.double("unit_discounted_price"),
      promotion_id = rs.longOpt("promotion_id"),
      promotion_type = rs.stringOpt("promotion_type"),
      unit_weight = rs.double("unit_weight"),
      image_url = rs.stringOpt("image_url")
    )
  }

  def getByShopAndUpdateTime(shop_id: Long, from: Long, to: Long): Try[Seq[OrderDetail]] = Try {
    val sql = s"""SELECT
                |ol.order_sn as order_sn,
                |ol.order_status as order_status,
                |ol.shop_id as shop_id,
                |ol.create_time as order_create_time,
                |ol.update_time as order_update_time,
                |ol.buyer_user_id as buyer_id,
                |ol.region as order_region,
                |ol.currency as currency,
                |ol.pay_time as order_pay_time,
                |ol.cancel_by as order_cancel_by,
                |ol.cancel_reason as order_cancel_reason,
                |ol.total_amount as order_total_amount,
                |od.item_id as item_id,
                |od.item_name as item_name,
                |od.item_sku as item_sku,
                |od.quantity as quantity,
                |od.originial_price as unit_original_price,
                |od.discounted_price as unit_discounted_price,
                |od.promotion_id as promotion_id,
                |od.promotion_type as promotion_type,
                |od.weight as unit_weight,
                |od.image_url as image_url
                | from
                |order_list as ol
                |inner join order_detail as od
                |on od.order_sn = ol.order_sn
                |where ol.shop_id = $shop_id
                |and ol.update_time >= $from
                |and ol.update_time < $to""".stripMargin
    def rsToString(resultSet: WrappedResultSet): Long = {
      val a = resultSet.long("quantity")
      println(a)
      a
    }
  SQL(sql)
    .map(convertToEntity)
    .list()
    .apply()
  }

  def testLocal(): Option[String] = {
    def getName(rs: WrappedResultSet): String = rs.string("Name")

    SQL("select * from category")
      .map(getName)
      .first()
      .apply()
  }


}


package anorm {

  import utils.Scala.MayErr
  import utils.Scala.MayErr._

  trait MParser13[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, R] extends ParserWithId[R] {

    val p1: ColumnTo[A1]

    val p2: ColumnTo[A2]

    val p3: ColumnTo[A3]

    val p4: ColumnTo[A4]

    val p5: ColumnTo[A5]

    val p6: ColumnTo[A6]

    val p7: ColumnTo[A7]

    val p8: ColumnTo[A8]

    val p9: ColumnTo[A9]

    val p10: ColumnTo[A10]

    val p11: ColumnTo[A11]

    val p12: ColumnTo[A12]

    val p13: ColumnTo[A13]

    def apply(a1: A1, a2: A2, a3: A3, a4: A4, a5: A5, a6: A6, a7: A7, a8: A8, a9: A9, a10: A10, a11: A11, a12: A12, a13: A13): R

    val containerName: String
    val columnNames: (String, String, String, String, String, String, String, String, String, String, String, String, String)

    lazy val (name1, name2, name3, name4, name5, name6, name7, name8, name9, name10, name11, name12, name13) = columnNames

    import SqlParser._
    override def apply(input: Input): SqlParser.ParseResult[R] =
      (get[A1](name1)(p1) ~<
        get[A2](name2)(p2) ~<
        get[A3](name3)(p3) ~<
        get[A4](name4)(p4) ~<
        get[A5](name5)(p5) ~<
        get[A6](name6)(p6) ~<
        get[A7](name7)(p7) ~<
        get[A8](name8)(p8) ~<
        get[A9](name9)(p9) ~<
        get[A10](name10)(p10) ~<
        get[A11](name11)(p11) ~<
        get[A12](name12)(p12) ~<
        get[A13](name13)(p13) ^^ { case a1 ~ a2 ~ a3 ~ a4 ~ a5 ~ a6 ~ a7 ~ a8 ~ a9 ~ a10 ~ a11 ~ a12 ~ a13 => apply(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13) })(input)

    val uniqueId: (Row => MayErr[SqlRequestError, Any]) = null
  }

  trait M13[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, R] {
    self: MParser13[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, R] =>

    val pt1: (ColumnTo[A1], ToStatement[A1])
    val pt2: (ColumnTo[A2], ToStatement[A2])
    val pt3: (ColumnTo[A3], ToStatement[A3])
    val pt4: (ColumnTo[A4], ToStatement[A4])
    val pt5: (ColumnTo[A5], ToStatement[A5])
    val pt6: (ColumnTo[A6], ToStatement[A6])
    val pt7: (ColumnTo[A7], ToStatement[A7])
    val pt8: (ColumnTo[A8], ToStatement[A8])
    val pt9: (ColumnTo[A9], ToStatement[A9])
    val pt10: (ColumnTo[A10], ToStatement[A10])
    val pt11: (ColumnTo[A11], ToStatement[A11])
    val pt12: (ColumnTo[A12], ToStatement[A12])
    val pt13: (ColumnTo[A13], ToStatement[A13])

    def unapply(r: R): Option[(A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13)]
    def unqualify(columnName: String) = columnName.split('.').last

    def update(v: R)(implicit connection: java.sql.Connection, hasId: (A1 <:< Pk[_]) |:| (A2 <:< Pk[_]) |:| (A3 <:< Pk[_]) |:| (A4 <:< Pk[_]) |:| (A5 <:< Pk[_]) |:| (A6 <:< Pk[_]) |:| (A7 <:< Pk[_]) |:| (A8 <:< Pk[_]) |:| (A9 <:< Pk[_]) |:| (A10 <:< Pk[_]) |:| (A11 <:< Pk[_]) |:| (A12 <:< Pk[_]) |:| (A13 <:< Pk[_])) = {

      val all = ((v, hasId) match {
        case (self(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13), (e1 |:| e2 |:| e3 |:| e4 |:| e5 |:| e6 |:| e7 |:| e8 |:| e9 |:| e10 |:| e11 |:| e12 |:| e13)) =>
          List(
            (e1, unqualify(name1), toParameterValue(a1)(pt1._2)),
            (e2, unqualify(name2), toParameterValue(a2)(pt2._2)),
            (e3, unqualify(name3), toParameterValue(a3)(pt3._2)),
            (e4, unqualify(name4), toParameterValue(a4)(pt4._2)),
            (e5, unqualify(name5), toParameterValue(a5)(pt5._2)),
            (e6, unqualify(name6), toParameterValue(a6)(pt6._2)),
            (e7, unqualify(name7), toParameterValue(a7)(pt7._2)),
            (e8, unqualify(name8), toParameterValue(a8)(pt8._2)),
            (e9, unqualify(name9), toParameterValue(a9)(pt9._2)),
            (e10, unqualify(name10), toParameterValue(a10)(pt10._2)),
            (e11, unqualify(name11), toParameterValue(a11)(pt11._2)),
            (e12, unqualify(name12), toParameterValue(a12)(pt12._2)),
            (e13, unqualify(name13), toParameterValue(a13)(pt13._2)))
      })

      val (ids, toSet) = all.partition(_._1.isDefined)
      if (ids == all) throw new Exception("everything is a Pk, nothing left to set!")

      val toUpdate = toSet.map(_._2).map(n => n + " = " + "{" + n + "}").mkString(", ")

      import Sql._

      sql("update " + containerName + " set " + toUpdate +
        " where " + ids.map(_._2).map(n => n + " = " + "{" + n + "}").mkString(" and "))
        .on(all.map(v => (v._2, v._3)): _*)
        .executeUpdate()

    }

    def create(v: R)(implicit connection: java.sql.Connection, hasId: (A1 <:< Pk[_]) |:| (A2 <:< Pk[_]) |:| (A3 <:< Pk[_]) |:| (A4 <:< Pk[_]) |:| (A5 <:< Pk[_]) |:| (A6 <:< Pk[_]) |:| (A7 <:< Pk[_]) |:| (A8 <:< Pk[_]) |:| (A9 <:< Pk[_]) |:| (A10 <:< Pk[_]) |:| (A11 <:< Pk[_]) |:| (A12 <:< Pk[_]) |:| (A13 <:< Pk[_])): R = {

      val all = ((v, hasId) match {
        case (self(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13), (e1 |:| e2 |:| e3 |:| e4 |:| e5 |:| e6 |:| e7 |:| e8 |:| e9 |:| e10 |:| e11 |:| e12 |:| e13)) =>
          List(
            (e1, unqualify(name1), toParameterValue(a1)(pt1._2)),
            (e2, unqualify(name2), toParameterValue(a2)(pt2._2)),
            (e3, unqualify(name3), toParameterValue(a3)(pt3._2)),
            (e4, unqualify(name4), toParameterValue(a4)(pt4._2)),
            (e5, unqualify(name5), toParameterValue(a5)(pt5._2)),
            (e6, unqualify(name6), toParameterValue(a6)(pt6._2)),
            (e7, unqualify(name7), toParameterValue(a7)(pt7._2)),
            (e8, unqualify(name8), toParameterValue(a8)(pt8._2)),
            (e9, unqualify(name9), toParameterValue(a9)(pt9._2)),
            (e10, unqualify(name10), toParameterValue(a10)(pt10._2)),
            (e11, unqualify(name11), toParameterValue(a11)(pt11._2)),
            (e12, unqualify(name12), toParameterValue(a12)(pt12._2)),
            (e13, unqualify(name13), toParameterValue(a13)(pt13._2)))
      })

      val (notSetIds, toSet) = all.partition(i => i._1.isDefined && i._3.aValue == NotAssigned)
      if (notSetIds.length > 1) throw new Exception("multi ids not supported")
      val toInsert = toSet.map(_._2)

      import Sql._
      import scala.util.control.Exception._
      import SqlParser._

      val idParser: SqlParser.Parser[_] = {
        SqlParser.RowParser(row =>
          row.asList.headOption.flatMap(a =>
            (if (a.isInstanceOf[Option[_]]) a else Option(a)).asInstanceOf[Option[_]]).toRight(NoColumnsInReturnedResult))
      }

      val (statement, ok) = sql("insert into " + containerName + " ( " + toInsert.mkString(", ") + " ) values ( " + toInsert.map("{" + _ + "}").mkString(", ") + ")")
        .on(all.map(v => (v._2, v._3)): _*)
        .execute1(getGeneratedKeys = true)

      val rs = statement.getGeneratedKeys();
      val id = idParser(StreamReader(Sql.resultSetToStream(rs))).get

      val List(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13) = all.map(_._3.aValue).map({ case NotAssigned => Id(id); case other => other })
      apply(a1.asInstanceOf[A1], a2.asInstanceOf[A2], a3.asInstanceOf[A3], a4.asInstanceOf[A4], a5.asInstanceOf[A5], a6.asInstanceOf[A6], a7.asInstanceOf[A7], a8.asInstanceOf[A8], a9.asInstanceOf[A9], a10.asInstanceOf[A10], a11.asInstanceOf[A11], a12.asInstanceOf[A12], a13.asInstanceOf[A13])

    }
  }

}


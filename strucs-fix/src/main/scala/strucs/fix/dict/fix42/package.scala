package strucs.fix.dict

import strucs.fix.{CodecFix, TagCodecFix}
import org.joda.time.DateTime
import strucs.Wrapper
import Wrapper.materializeWrapper
import strucs.Wrapper

/**
 * Tag names for FIX 4.2
 */
package object fix42 {

  case class BeginString(v: String) extends AnyVal
  object BeginString {
    val Fix42 = BeginString("FIX.4.2")
    val Tag = 8
    implicit val codec = new TagCodecFix[BeginString, String](Tag)

    val Fix42TV = codec.encode(Fix42)
  }

  case class BodyLength(v: Int) extends AnyVal
  object BodyLength {
    implicit val codec: CodecFix[BodyLength] = new TagCodecFix[BodyLength, Int](9)
  }

  case class CheckSum(v: Int) extends AnyVal
  object CheckSum {
    implicit val codec: CodecFix[CheckSum] = new TagCodecFix[CheckSum, Int](10)
  }

  case class ClOrdId(v: String) extends AnyVal
  object ClOrdId {
    implicit val codec: CodecFix[ClOrdId] = new TagCodecFix[ClOrdId, String](11)
  }


  case class MsgSeqNum(v: String) extends AnyVal
  object MsgSeqNum {
    implicit val codec: CodecFix[MsgSeqNum] = new TagCodecFix[MsgSeqNum, String](34)
  }

  case class MsgType(v: String) extends AnyVal
  object MsgType {
    val OrderSingle = MsgType("D")
    val Logon = MsgType("A")
    val Tag = 35
    implicit val codec = new TagCodecFix[MsgType, String](Tag)

    val OrderSingleTV = codec.encode(OrderSingle)
    val LogonTV = codec.encode(Logon)
  }

  case class OrderQty(v: BigDecimal) extends AnyVal
  object OrderQty {
    implicit val codec: CodecFix[OrderQty] = new TagCodecFix[OrderQty, BigDecimal](38)
  }




  case class SenderCompID(v: String) extends AnyVal
  object SenderCompID {
    implicit val codec: CodecFix[SenderCompID] = new TagCodecFix[SenderCompID, String](49)
  }

  case class SendingTime(v: DateTime) extends AnyVal
  object SendingTime {
    implicit val codec: CodecFix[SendingTime] = new TagCodecFix[SendingTime, DateTime](52)
  }


  /** Example of a lenient enumeration.
    * We just provide some common constants but the user of the library can add his own */
  case class OrderCapacity(v: String) extends AnyVal
  object OrderCapacity {
    val AgencySingleOrder = OrderCapacity("A")
    val ShortExampleTransaction = OrderCapacity("B")

    implicit val codec: CodecFix[OrderCapacity] = new TagCodecFix[OrderCapacity, String](47)
  }

  /** Example of a more strict enumeration.
    * This can be pattern-matched with a compiler warning for missing patterns.
    * We still allow other values, but it is materialized with a "Other" type */
  sealed abstract class OrdType(val v: String)
  object OrdType {
    case object Market extends OrdType("1")
    case object Limit  extends OrdType("2")
    case object Stop   extends OrdType("3")
    case class Other(_v: String) extends OrdType(_v)

    val all = Seq(Market, Limit, Stop)
    def make(fixValue: String): Option[OrdType] = all.find(_.v == fixValue).orElse(Some(Other(fixValue)))

    // decoding a 54=X if X is not declared in 'all' would fail
    implicit val wrapper: Wrapper[OrdType, String] = Wrapper(make, _.v)
    implicit val codec: CodecFix[OrdType] = new TagCodecFix[OrdType, String](40)
  }


  /** Example of a strict enumeration, the only values that can encoded/decoded are declared here */
  sealed abstract class Side(val v: String)
  object Side {
    case object Buy extends Side("1")
    case object Sell extends Side("2")

    val all = Seq(Buy, Sell)
    def make(fixValue: String): Option[Side] = all.find(_.v == fixValue)

    // decoding a 54=X if X is not declared in 'all' would fail
    implicit val wrapper: Wrapper[Side, String] = Wrapper(make, _.v)
    implicit val codec: CodecFix[Side] = new TagCodecFix[Side, String](54)
  }

  sealed abstract class HandlInst(val v: String)
  object HandlInst {
    case object AutomatedPrivateNoBroker extends HandlInst("1")
    case object AutomatedPublicBrokerOk extends HandlInst("2")
    case object ManualBest extends HandlInst("3")

    val all = Seq(AutomatedPrivateNoBroker, AutomatedPublicBrokerOk, ManualBest)
    def make(fixValue: String): Option[HandlInst] = all.find(_.v == fixValue)

    implicit val wrapper: Wrapper[HandlInst, String] = Wrapper(make, _.v)
    implicit val codec: CodecFix[HandlInst] = new TagCodecFix[HandlInst, String](21)
  }


  case class Symbol(v: String) extends AnyVal
  object Symbol {
    implicit val codec: CodecFix[Symbol] = new TagCodecFix[Symbol, String](55)
  }

  case class TargetCompID(v: String) extends AnyVal
  object TargetCompID {
    implicit val codec: CodecFix[TargetCompID] = new TagCodecFix[TargetCompID, String](56)
  }

  case class TimeInForce(v: String) extends AnyVal
  object TimeInForce {
    implicit val codec: CodecFix[TimeInForce] = new TagCodecFix[TimeInForce, String](59)
  }

  case class TransactTime(v: DateTime) extends AnyVal
  object TransactTime {
    implicit val codec: CodecFix[TransactTime] = new TagCodecFix[TransactTime, DateTime](60)
  }


  case class OnBehalfOfCompID(v: String) extends AnyVal
  object OnBehalfOfCompID {
    implicit val codec: CodecFix[OnBehalfOfCompID] = new TagCodecFix[OnBehalfOfCompID, String](115)
  }

  case class SecurityExchange(v: String) extends AnyVal
  object SecurityExchange {
    val NYSE = SecurityExchange("N")
    implicit val codec: CodecFix[SecurityExchange] = new TagCodecFix[SecurityExchange, String](207)
  }



}

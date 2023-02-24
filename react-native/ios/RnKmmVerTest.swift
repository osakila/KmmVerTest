
import KmmVerTest

@objc(RnKmmVerTest)
class RnKmmVerTest: NSObject {

  @objc(multiply:withB:withResolver:withRejecter:)
  func multiply(a: Float, b: Float, resolve:RCTPromiseResolveBlock,reject:RCTPromiseRejectBlock) -> Void {
    resolve(a*b)
  }

    @objc(getPlatformName:withRejecter:)
    func getPlatformName(resolve:RCTPromiseResolveBlock,reject:RCTPromiseRejectBlock) -> Void {
        resolve(PlatformKt.getPlatform().name)
    }
}

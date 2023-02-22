//
//  ViewController.swift
//  demo-ios
//
//  Created by osaki on 2023/02/22.
//

import UIKit
import KmmVerTest

class ViewController: UIViewController {

    @IBOutlet weak var label: UILabel!
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        label.text = PlatformKt.getPlatform().name
    }
}

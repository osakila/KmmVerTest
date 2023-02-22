Pod::Spec.new do |spec|
    spec.name                     = 'KmmVerTest'
    spec.version                  = '1.0.0'
    spec.homepage                 = 'https://github.com/osakila/KmmVerTest'
    spec.source                   = { :http => 'https://github.com/osakila/KmmVerTest/releases/download/1.0.0/KmmVerTest.xcframework.zip' }
    spec.authors                  = 'LASSIC'
    spec.license                  = 'MIT'
    spec.summary                  = 'Some description for the KmmVerTest Module'
    spec.vendored_frameworks      = 'KmmVerTest.xcframework'
    spec.libraries                = 'c++'
    spec.ios.deployment_target = '14.0'
                
                
                
                
end
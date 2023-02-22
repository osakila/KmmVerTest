Pod::Spec.new do |spec|
    spec.name                     = 'KmmVerTest'
    spec.version                  = '1.0.1'
    spec.homepage                 = 'https://github.com/osakila/KmmVerTest'
    spec.source                   = { :http=> ''}
    spec.authors                  = 'LASSIC'
    spec.license                  = 'MIT'
    spec.summary                  = 'Some description for the KmmVerTest Module'
    spec.vendored_frameworks      = 'build/cocoapods/framework/KmmVerTest.framework'
    spec.libraries                = 'c++'
    spec.ios.deployment_target = '14.0'
                
                
    spec.pod_target_xcconfig = {
        'KOTLIN_PROJECT_PATH' => ':kotlin-multiplatform',
        'PRODUCT_MODULE_NAME' => 'KmmVerTest',
    }
                
    spec.script_phases = [
        {
            :name => 'Build KmmVerTest',
            :execution_position => :before_compile,
            :shell_path => '/bin/sh',
            :script => <<-SCRIPT
                if [ "YES" = "$OVERRIDE_KOTLIN_BUILD_IDE_SUPPORTED" ]; then
                  echo "Skipping Gradle build task invocation due to OVERRIDE_KOTLIN_BUILD_IDE_SUPPORTED environment variable set to \"YES\""
                  exit 0
                fi
                set -ev
                REPO_ROOT="$PODS_TARGET_SRCROOT"
                "$REPO_ROOT/../gradlew" -p "$REPO_ROOT" $KOTLIN_PROJECT_PATH:syncFramework \
                    -Pkotlin.native.cocoapods.platform=$PLATFORM_NAME \
                    -Pkotlin.native.cocoapods.archs="$ARCHS" \
                    -Pkotlin.native.cocoapods.configuration="$CONFIGURATION"
            SCRIPT
        }
    ]
                
end
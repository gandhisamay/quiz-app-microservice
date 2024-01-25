@Library('ci@master')

MavenDockerPublish(
    'mavenDeployCommand': 'mvn -U clean package -DskipTests',
    'tenant': 'tacs',
    'jdkVersion': '17',
    'enableCodeSonarSastScan': false,
    'enableCodeSonarSastGating': false,
    'codeSonarScanCommand': '',
    'disableImagePublish': false
)
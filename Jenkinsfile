node {

    String mavenVersion = ""
    String gitCommit = ""

    stage('Git Checkout') {
        git credentialsId: 'marco-git', url: 'https://github.com/marcobrasci1986/jenkins-sandbox-private.git'

    }

    /**
     * How to set env variable:
     * env.JENKINS_SANDBOX_TAG = "latest"
     *
     * user as $JENKINS_SANDBOX_TAG in kubernetes files for interpolation
     *
     */
    stage("Initialize environment variables") {
        mavenVersion = readMavenPom().getVersion()
        gitCommit = sh(returnStdout: true, script: 'git rev-parse --short HEAD').trim()
        env.JENKINS_SANDBOX_TAG = sh(returnStdout: true, script: "echo ${mavenVersion}-${gitCommit}").trim()

        echo "JENKINS_SANDBOX_TAG : " + env.JENKINS_SANDBOX_TAG
    }


    stage('Mvn build') {
        sh "mvn clean install"
    }

    // TODO do a release. First setup ssh connection with Bitbucket
    stage('Mvn release') {
//        sh "git config user.name 'marcobrasci1986'"
//        sh "git config user.email 'marco_brasci@hotmail.com'"

        sh "mvn release:clean"
        sh "mvn -B release:prepare release:perform"
    }

    /**
     * Based on https://jenkins.io/doc/book/pipeline/docker/
     */
//    stage('Build and push docker image') {
//
//        // vutg_docker is defined in Jenkins Credentials
//        docker.withRegistry('https://bld-vutgnexus-01.kindengezin.be:5000', 'vutg_docker') {
//
//            def image = docker.build("vutg/jenkins-sandbox:${env.JENKINS_SANDBOX_TAG}")
//            image.push()
//        }
//    }

//    stage('K8s deploy on BLD') {
//        kubernetesDeploy(kubeconfigId: 'kubeconfig-bld', configs: 'kubernetes/bld/1_jenkins-sandbox-service.yaml', enableConfigSubstitution: false)
//        kubernetesDeploy(kubeconfigId: 'kubeconfig-bld', configs: 'kubernetes/bld/2_jenkins-sandbox-config-map.yaml', enableConfigSubstitution: false)
//        kubernetesDeploy(kubeconfigId: 'kubeconfig-bld', configs: 'kubernetes/bld/3_jenkins-sandbox-deployment.yaml', enableConfigSubstitution: true)
//        kubernetesDeploy(kubeconfigId: 'kubeconfig-bld', configs: 'kubernetes/bld/4_jenkins-sandbox-ingress.yaml', enableConfigSubstitution: false)
//    }
}

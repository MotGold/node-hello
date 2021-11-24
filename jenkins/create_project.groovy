job('NodeJS Docker example') {
    scm {
        git('git://github.com/MotGold/node-hello.git') {  node -> // is hudson.plugins.git.GitSCM
            node / gitConfigName('MotGold')
            node / gitConfigEmail('motgold@walla.co.il')
        }
    }
    triggers {
        scm('H/5 * * * *')
    }
    wrappers {
        nodejs('Node 17.1.0') // this is the name of the NodeJS installation in 
                         // Manage Jenkins -> Configure Tools -> NodeJS Installations -> Name
    }
    steps {
        dockerBuildAndPublish {
            repositoryName('motgold/compie-jenkins-1')
            tag('${GIT_REVISION,length=9}')
            registryCredentials('dockerhub')
            forcePull(false)
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
}


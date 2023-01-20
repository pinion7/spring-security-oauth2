dependencies {
    implementation("org.springframework.security:spring-security-oauth2-authorization-server:1.0.0")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:Hoxton.SR1")
    }
}

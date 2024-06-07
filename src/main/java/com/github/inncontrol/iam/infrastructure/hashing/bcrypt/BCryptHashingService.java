package com.github.inncontrol.iam.infrastructure.hashing.bcrypt;

import com.github.inncontrol.iam.application.internal.outboundservices.hashing.HashingService;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface BCryptHashingService  extends HashingService, PasswordEncoder {

}

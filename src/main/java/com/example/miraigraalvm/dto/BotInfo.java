package com.example.miraigraalvm.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.aot.hint.annotation.RegisterReflectionForBinding;

@Getter
@Setter
@RegisterReflectionForBinding
public class BotInfo {
    private long account;
    private String password;
    private long priority;
}

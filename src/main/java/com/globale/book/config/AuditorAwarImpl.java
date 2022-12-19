package com.globale.book.config;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;

public class AuditorAwarImpl  implements AuditorAware<String>{
@Override
public Optional<String> getCurrentAuditor() {
	// TODO Auto-generated method stub
	return Optional.of("tabat abdelfettah:)");
}
}

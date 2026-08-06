package com.traderisk.portfolio;

import com.traderisk.user.AppUser;
import com.traderisk.user.AppUserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PortfolioService {

    private final PortfolioRepository portfolioRepository;
    private final AppUserRepository appUserRepository;

    public PortfolioService(PortfolioRepository portfolioRepository, AppUserRepository appUserRepository) {
        this.portfolioRepository = portfolioRepository;
        this.appUserRepository = appUserRepository;
    }

    public PortfolioResponse createPortfolio(CreatePortfolioRequest request) {
        AppUser ownerUser = appUserRepository.findById(request.ownerUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + request.ownerUserId()));

        BigDecimal startingCashBalance = request.cashBalance() != null
                ? request.cashBalance()
                : BigDecimal.ZERO;

        String baseCurrency = request.baseCurrency() != null
                ? request.baseCurrency()
                : "USD";

        Portfolio portfolio = new Portfolio(
                ownerUser,
                request.name(),
                baseCurrency,
                startingCashBalance
        );

        Portfolio savedPortfolio = portfolioRepository.save(portfolio);

        return PortfolioResponse.from(savedPortfolio);
    }

    public List<PortfolioResponse> getAllPortfolios() {
        return portfolioRepository.findAll()
                .stream()
                .map(PortfolioResponse::from)
                .toList();
    }

    public PortfolioResponse getPortfolioById(Long id) {
        Portfolio portfolio = portfolioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Portfolio not found with id: " + id));

        return PortfolioResponse.from(portfolio);
    }

    public List<PortfolioResponse> getPortfoliosByUserId(Long userId) {
        if (!appUserRepository.existsById(userId)) {
            throw new IllegalArgumentException("User not found with id: " + userId);
        }

        return portfolioRepository.findByOwnerUserId(userId)
                .stream()
                .map(PortfolioResponse::from)
                .toList();
    }
}
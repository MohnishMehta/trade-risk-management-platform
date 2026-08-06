package com.traderisk.portfolio;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PortfolioController {

    private final PortfolioService portfolioService;

    public PortfolioController(PortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }

    @PostMapping("/portfolios")
    public PortfolioResponse createPortfolio(@RequestBody CreatePortfolioRequest request) {
        return portfolioService.createPortfolio(request);
    }

    @GetMapping("/portfolios")
    public List<PortfolioResponse> getAllPortfolios() {
        return portfolioService.getAllPortfolios();
    }

    @GetMapping("/portfolios/{id}")
    public PortfolioResponse getPortfolioById(@PathVariable Long id) {
        return portfolioService.getPortfolioById(id);
    }

    @GetMapping("/users/{userId}/portfolios")
    public List<PortfolioResponse> getPortfoliosByUserId(@PathVariable Long userId) {
        return portfolioService.getPortfoliosByUserId(userId);
    }
}
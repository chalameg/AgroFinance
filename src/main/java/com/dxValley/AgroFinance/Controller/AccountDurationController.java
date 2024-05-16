package com.dxValley.AgroFinance.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dxValley.AgroFinance.DTO.AccountDurationDTO;
import com.dxValley.AgroFinance.Models.AccountDuration;
import com.dxValley.AgroFinance.Service.AccountDurationService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequestMapping("/api/accountDurations")
@Tag(name = "Account Duration APIs.")
@RestController
@CrossOrigin(origins = {"*"}, maxAge = 3600L)
public class AccountDurationController {

    private final AccountDurationService accountDurationService;

    public AccountDurationController(AccountDurationService accountDurationService) {
        this.accountDurationService = accountDurationService;
    }

    @GetMapping("/default")
    public ResponseEntity<List<AccountDuration>> getDefaultAccountDurations() {
        List<AccountDuration> accountDurations = accountDurationService.getDefaultAccountDurations();
        return ResponseEntity.ok(accountDurations);
    }


    @GetMapping("/cohort/{cohortId}")
    public ResponseEntity<List<AccountDuration>> getCohortAccountDurations(@PathVariable Long cohortId) {
        List<AccountDuration> accountDurations = accountDurationService.getCohortAccountDurations(cohortId);
        return ResponseEntity.ok(accountDurations);
    }

    @PostMapping
    public ResponseEntity<AccountDuration> createAccountDuration(@RequestBody @Valid AccountDurationDTO AccountDurationDTO) {
        AccountDuration createdAccountDuration = accountDurationService.createAccountDuration(AccountDurationDTO);
        return ResponseEntity.ok(createdAccountDuration);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountDuration> updateAccountDuration(@PathVariable Long id, @RequestBody AccountDurationDTO AccountDurationDTO) {
        AccountDuration AccountDuration = accountDurationService.updateAccountDuration(id, AccountDurationDTO);
        return ResponseEntity.ok(AccountDuration);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteAccountDuration(@PathVariable Long id) {
        accountDurationService.deleteAccountDuration(id);
        
        return ResponseEntity.ok("Deleted!");
    }
}

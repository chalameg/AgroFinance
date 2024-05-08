package com.dxValley.AgroFinance.Service;

import com.dxValley.AgroFinance.DTO.AccountDurationDTO;
import com.dxValley.AgroFinance.Models.AccountDuration;
import com.dxValley.AgroFinance.Models.Cohort;
import com.dxValley.AgroFinance.Repository.AccountDurationRepository;
import com.dxValley.AgroFinance.exceptions.customExceptions.ResourceNotFoundException;
import com.dxValley.AgroFinance.utils.ObjectPropertyUtils;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountDurationService {

    private final AccountDurationRepository accountDurationRepository;
    private final CohortService cohortService;

    public AccountDurationService(AccountDurationRepository accountDurationRepository, CohortService cohortService) {
        this.accountDurationRepository = accountDurationRepository;
        this.cohortService = cohortService;
    }

    public List<AccountDuration> getDefaultAccountDurations() {
        return accountDurationRepository.findByCohortIsNull();
    }

    public List<AccountDuration> getCohortAccountDurations(Long cohortId) {
        return accountDurationRepository.findByCohortId(cohortId);
    }

    public AccountDuration createAccountDuration(AccountDurationDTO accountDurationDTO) {

        Long cohortId = accountDurationDTO.getCohortId();
        Cohort cohort = cohortId != null ? cohortService.getCohortById(cohortId) : null;

        AccountDuration accountDuration = new AccountDuration();
        accountDuration.setCohort(cohort);
        accountDuration.setMaxMonth(accountDurationDTO.getMaxMonth());
        accountDuration.setMinMonth(accountDurationDTO.getMinMonth());
        accountDuration.setDescription(accountDurationDTO.getDescription());
        accountDuration.setMinWeight(accountDurationDTO.getMinWeight());

        return accountDurationRepository.save(accountDuration);
    }

    public AccountDuration updateAccountDuration(Long id, AccountDurationDTO accountDurationDTO) {
        AccountDuration accountDuration = getAccountDurationById(id);
        // Copy non-null properties from DTO to Entity
        BeanUtils.copyProperties(accountDurationDTO, accountDuration, ObjectPropertyUtils.getNullPropertyNames(accountDurationDTO));
        return accountDurationRepository.save(accountDuration);
    }

    public void deleteAccountDuration(Long id) {
        AccountDuration AccountDuration = getAccountDurationById(id);
        accountDurationRepository.delete(AccountDuration);
    }

    private AccountDuration getAccountDurationById(Long id) {
        return accountDurationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("AccountDuration with ID " + id + " not found"));
    }
}

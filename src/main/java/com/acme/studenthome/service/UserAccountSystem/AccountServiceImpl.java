package com.acme.studenthome.service.UserAccountSystem;

import com.acme.studenthome.domain.model.UserAccountSystem.Account;
import com.acme.studenthome.domain.model.UserAccountSystem.User;




import com.acme.studenthome.domain.repository.UserAccountSystemRepository.AccountRepository;
import com.acme.studenthome.domain.repository.UserAccountSystemRepository.UserRepository;
import com.acme.studenthome.domain.service.UserAccountSystemService.AccountService;

import com.acme.studenthome.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<Account> getAllAccountsByUserId(Long userId, Pageable pageable) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("User", "Id", userId);
        }
        return accountRepository.findByUserId(userId, pageable);
    }

    @Override
    public Account createAccount(Long userId, Account account) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        account.setUser(user);
        return accountRepository.save(account);
    }

    @Override
    public Account getAccountByIdAndUserId(Long accountId, Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("User", "Id", userId);
        }
        return accountRepository.findByIdAndUserId(accountId, userId)
                .orElseThrow(() -> new ResourceNotFoundException("Account", "Id", accountId));
    }

    @Override
    public Account updateAccount(Long accountId, Long userId, Account accountRequest) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("User", "Id", userId);
        }
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Account", "Id", accountId));
        account.setDni(accountRequest.getDni());
        account.setFirstName(accountRequest.getFirstName());
        account.setLastName(accountRequest.getLastName());
        account.setPhone(accountRequest.getPhone());
        return accountRepository.save(account);
    }

    @Override
    public ResponseEntity<?> deleteAccount(Long accountId, Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("User", "Id", userId);
        }
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Account", "Id", accountId));

        accountRepository.delete(account);

        return ResponseEntity.ok().build();
    }
}

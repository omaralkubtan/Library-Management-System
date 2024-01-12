package com.legacy.lms.service;

import com.legacy.lms.dto.Patron.PatronCreateDto;
import com.legacy.lms.dto.Patron.PatronUpdateDto;
import com.legacy.lms.entity.Patron;
import com.legacy.lms.error.exceptions.BadRequestException;
import com.legacy.lms.repository.PatronRepository;
import com.legacy.lms.util.helper.PaginationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.legacy.lms.util.localization.Tokens;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Service
public class PatronService {

    @Autowired
    private PatronRepository patronRepository;

    public PaginationResult<Patron> getAllPatrons(int pageNumber, int pageSize) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        Page<Patron> patronsPage = patronRepository.findAll(pageable);

        return new PaginationResult<>(patronsPage);
    }

    public Patron getPatron(Long id) {
        var patron = patronRepository.findById(id);

        if (patron.isEmpty() || patron.get().getIsDeleted()) {
            throw new EntityNotFoundException(Tokens.M_PATRON_NOT_FOUND);
        }

        return patron.get();
    }

    @Transactional
    public Patron createPatron(PatronCreateDto request) {
//        var existingPatron = patronRepository.findByFirstNameAndLastName(request.getFirstName(), request.getLastName());
//
//        if(existingPatron.isPresent())
//            throw new BadRequestException(Tokens.PATRON_ALREADY_EXIST);

        var patron = new Patron();
        patron.setFirstName(request.getFirstName());
        patron.setLastName(request.getLastName());
        patron.setMobile(request.getMobile());
        patron.setAddress(request.getAddress());

        return patronRepository.save(patron);
    }

    @Transactional
    public Patron updatePatron(Long id, PatronUpdateDto request) {
        var patron = getPatron(id);

        patron.setFirstName(request.getFirstName());
        patron.setLastName(request.getLastName());
        patron.setMobile(request.getMobile());
        patron.setAddress(request.getAddress());

        return patronRepository.save(patron);
    }

    @Transactional
    public void deletePatron(Long id) {
        var patron = getPatron(id);

        if (patron.getDeletedAt() != null) {
            throw new BadRequestException(Tokens.M_PATRON_ALREADY_DELETED);
        }

        patron.delete();

        patronRepository.save(patron);
    }
}

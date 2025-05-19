package elmoutaouaki.abderrahim.backend.services.impl;

import elmoutaouaki.abderrahim.backend.dtos.CreditDTO;
import elmoutaouaki.abderrahim.backend.entities.Credit;
import elmoutaouaki.abderrahim.backend.mappers.CreditMapper;
import elmoutaouaki.abderrahim.backend.repositories.CreditRepository;
import elmoutaouaki.abderrahim.backend.services.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CreditServiceImpl implements CreditService {

    private final CreditRepository creditRepository;
    private final CreditMapper creditMapper;

    @Autowired
    public CreditServiceImpl(CreditRepository creditRepository, CreditMapper creditMapper) {
        this.creditRepository = creditRepository;
        this.creditMapper = creditMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CreditDTO> findAll() {
        return creditRepository.findAll().stream()
                .map(creditMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CreditDTO> findById(Long id) {
        return creditRepository.findById(id)
                .map(creditMapper::toDto);
    }

    @Override
    public CreditDTO save(CreditDTO creditDTO) {
        Credit credit = creditMapper.toEntity(creditDTO);
        credit = creditRepository.save(credit);
        return creditMapper.toDto(credit);
    }

    @Override
    public void deleteById(Long id) {
        creditRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CreditDTO> findByClientId(Long clientId) {
        return creditRepository.findByClientId(clientId).stream()
                .map(creditMapper::toDto)
                .collect(Collectors.toList());
    }
}

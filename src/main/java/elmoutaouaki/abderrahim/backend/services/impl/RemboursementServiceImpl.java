package elmoutaouaki.abderrahim.backend.services.impl;

import elmoutaouaki.abderrahim.backend.dtos.RemboursementDTO;
import elmoutaouaki.abderrahim.backend.entities.Remboursement;
import elmoutaouaki.abderrahim.backend.mappers.RemboursementMapper;
import elmoutaouaki.abderrahim.backend.repositories.RemboursementRepository;
import elmoutaouaki.abderrahim.backend.services.RemboursementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class RemboursementServiceImpl implements RemboursementService {

    private final RemboursementRepository remboursementRepository;
    private final RemboursementMapper remboursementMapper;

    @Autowired
    public RemboursementServiceImpl(RemboursementRepository remboursementRepository, RemboursementMapper remboursementMapper) {
        this.remboursementRepository = remboursementRepository;
        this.remboursementMapper = remboursementMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<RemboursementDTO> findAll() {
        return remboursementRepository.findAll().stream()
                .map(remboursementMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<RemboursementDTO> findById(Long id) {
        return remboursementRepository.findById(id)
                .map(remboursementMapper::toDto);
    }

    @Override
    public RemboursementDTO save(RemboursementDTO remboursementDTO) {
        Remboursement remboursement = remboursementMapper.toEntity(remboursementDTO);
        remboursement = remboursementRepository.save(remboursement);
        return remboursementMapper.toDto(remboursement);
    }

    @Override
    public void deleteById(Long id) {
        remboursementRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RemboursementDTO> findByCreditId(Long creditId) {
        return remboursementRepository.findByCreditId(creditId).stream()
                .map(remboursementMapper::toDto)
                .collect(Collectors.toList());
    }
}

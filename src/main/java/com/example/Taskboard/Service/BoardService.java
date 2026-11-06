package com.example.Taskboard.Service;

import com.example.Taskboard.Model.Board;
import com.example.Taskboard.Model.User;
import com.example.Taskboard.Repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Couche Service pour la gestion des tableaux (Board).
 */
@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserService userService;

    /**
     * Creer un nouveau tableau pour un utilisateur.
     * On verifie que le proprietaire (owner) existe avant de sauvegarder.
     */
    public Board createBoard(Long ownerId, Board board) {
        // On utilise getUserEntityById pour recuperer l'entite User et non le DTO
        User owner = userService.getUserEntityById(ownerId);
        board.setOwner(owner);
        return boardRepository.save(board);
    }

    /**
     * Recuperer tous les tableaux d'un utilisateur.
     */
    public List<Board> getBoardsByOwner(Long ownerId) {
        return boardRepository.findByOwnerId(ownerId);
    }

    /**
     * Recuperer un tableau par son identifiant.
     */
    public Board getBoardById(Long id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tableau introuvable avec id=" + id));
    }

    /**
     * Supprimer un tableau.
     */
    public void deleteBoard(Long id) {
        Board board = getBoardById(id);
        boardRepository.delete(board);
    }
}
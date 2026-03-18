package com.example.skunivProject.domain.chat.controller;

import com.example.skunivProject.domain.chat.dto.ResponseDto;
import com.example.skunivProject.domain.chat.entity.ChatMessage;
import com.example.skunivProject.domain.chat.entity.ChatRoom;
import com.example.skunivProject.domain.chat.service.ChatMessageService;
import com.example.skunivProject.domain.chat.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ChatController {

    private final ChatRoomService chatRoomService;
    private final ChatMessageService chatMessageService;

    
    /**
     * 팀 채팅방 생성
     */
    @PostMapping("/teams/{teamId}/chat-room")
    public ResponseEntity<ResponseDto.ChatRoomCreation> createChatRoom(
            @PathVariable Long teamId,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        ChatRoom newChatRoom = chatRoomService.createChatRoom(teamId, userDetails.getUsername());

        ResponseDto.ChatRoomCreation response = ResponseDto.ChatRoomCreation.builder()
                .roomId(newChatRoom.getId())
                .roomName(newChatRoom.getName())
                .teamId(newChatRoom.getTeamId())
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * 내가 속한 채팅방 목록 조회
     */
    @GetMapping("/my/chat-rooms")
    public ResponseEntity<List<ResponseDto.ChatRoomInfo>> getMyChatRooms(
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        List<ResponseDto.ChatRoomInfo> response = chatRoomService.getMyChatRooms(userDetails.getUsername());
        return ResponseEntity.ok(response);
    }

    /**
     * 특정 채팅방의 이전 대화 내역 조회
     */
    // TODO @PreAuthorize() + Spel
    @GetMapping("/chat/rooms/{roomId}/messages")
    public ResponseEntity<List<ChatMessage>> getChatMessages(
            @PathVariable String roomId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "150") int size
    ) {

        List<ChatMessage> messages = chatMessageService.getChatMessages(roomId, page, size);
        return ResponseEntity.ok(messages);
    }
}

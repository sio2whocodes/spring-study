package sungshin.sooon.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sungshin.sooon.dto.*;
import sungshin.sooon.model.Account;
import sungshin.sooon.model.CurrentUser;
import sungshin.sooon.model.Post;
import sungshin.sooon.repository.PostRepository;
import sungshin.sooon.service.PostService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/post")
public class PostController {

    private final PostService postService;
    private final PostRepository postRepository;

    //게시글
    @PostMapping("/create")
    public Long create(@CurrentUser Account account, @RequestBody PostCreateRequestDto dto){
        if(account != null){
            dto.setAccount(account);
        }
        return postService.save(dto);
    }

    @GetMapping("/{post_id}")
    public PostResponseDto read(@PathVariable Long post_id){
        return postService.read(post_id);
    }

    @PutMapping("/{post_id}")
    public Long update(@PathVariable Long post_id, @RequestBody PostUpdateRequestDto dto){
        return postService.update(post_id, dto);
    }

    @GetMapping("/{post_id}/delete")
    public String delete(@PathVariable Long post_id){
        postService.delete(post_id);
        return "redirect:/";
    }

    @GetMapping("/board")
    public String boardView(@PageableDefault Pageable pageable, Model model){
        Page<Post> postList = postService.getPostList(pageable);
        model.addAttribute("postList", postList);

        log.debug("총 element 수 : {}, 전체 page 수 : {}, 페이지에 표시할 element 수 : {}, 현재 페이지 index : {}, 현재 페이지의 element 수 : {}",
                postList.getTotalElements(), postList.getTotalPages(), postList.getSize(),
                postList.getNumber(), postList.getNumberOfElements());

        return "board";
    }

    //좋아요

    @RequestMapping("/{post_id}/like")
    public Long like(@PathVariable Long post_id, @CurrentUser Account account){
        PostLikeRequestDto dto = new PostLikeRequestDto();
        dto.setAccount(account);
        Post post = postRepository.findById(post_id)
                .orElseThrow(()->new IllegalArgumentException("["+post_id+"] 해당 게시글이 없습니다."));
        dto.setPost(post);
        return postService.like(dto);
    }

    @GetMapping("/{post_id}/dislike")
    public void dislike(@PathVariable Long post_id, @RequestBody Long post_like_id){
        postService.dislike(post_like_id);
    }

    //댓글

    @PostMapping("/{post_id}/comment/create")
    public Long createPostComment(@RequestBody PostCommentCreateRequestDto dto, @CurrentUser Account account){
        if(account != null){
            dto.setAccount(account);
        }
        return postService.createPostComment(dto).getPost_comment_id();
    }

    @GetMapping("/{post_id}/comment/delete")
    public void deletePostComment(@RequestBody Long post_comment_id){
        postService.deletePostComment(post_comment_id);
    }
}

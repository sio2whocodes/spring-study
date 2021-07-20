package sungshin.sooon.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sungshin.sooon.dto.PostCreateRequestDto;
import sungshin.sooon.dto.PostLikeRequestDto;
import sungshin.sooon.dto.PostResponseDto;
import sungshin.sooon.dto.PostUpdateRequestDto;
import sungshin.sooon.service.PostService;

import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/post")
public class PostController {

    private final PostService postService;

    @PostMapping("/create")
    public Long create(@RequestBody PostCreateRequestDto dto){
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

    @RequestMapping("/{post_id}/like")
    public Long like(@PathVariable Long post_id, HttpSession session){
        PostLikeRequestDto dto = new PostLikeRequestDto();
        dto.setPost_id(post_id);
        //JWT
//        dto.setAccount_id(Long.parseLong(session.getId()));
        return postService.like(dto);
    }

    @GetMapping("{post_id}/{post_like_id}")
    public void dislike(@PathVariable Long post_like_id){
        postService.dislike(post_like_id);
    }

}

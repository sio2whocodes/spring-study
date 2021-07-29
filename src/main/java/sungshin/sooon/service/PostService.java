package sungshin.sooon.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sungshin.sooon.dto.PostCommentRequestDto;
import sungshin.sooon.dto.PostCommentResponseDto;
import sungshin.sooon.dto.PostRequestDto;
import sungshin.sooon.dto.PostResponseDto;
import sungshin.sooon.model.Account;
import sungshin.sooon.model.Post;
import sungshin.sooon.model.PostComment;
import sungshin.sooon.model.PostLike;
import sungshin.sooon.repository.PostCommentRepository;
import sungshin.sooon.repository.PostLikeRepository;
import sungshin.sooon.repository.PostRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    @Autowired
    private final PostRepository postRepository;

    @Autowired
    private final PostLikeRepository postLikeRepository;

    @Autowired
    private final PostCommentRepository postCommentRepository;

    //게시글

    @Transactional
    public PostResponseDto save(Account account, PostRequestDto dto){
        Post post = dto.toPost();
        post.setAccount(account);
        return PostResponseDto.of(postRepository.save(post));
    }

    @Transactional
    public PostResponseDto update(Account account, Long id, PostRequestDto request){
        Post post = postRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("[게시글:"+id+"] 해당 게시글이 없습니다."));
        //account 검증
        post.update(request.getTitle(), request.getContent(), request.is_anonymous());
        return PostResponseDto.of(post);
    }

    @Transactional(readOnly = true)
    public PostResponseDto read(Long id){
        Post post = postRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("[게시글:"+id+"] 해당 게시글이 없습니다."));

        return PostResponseDto.of(post);
    }

    @Transactional
    public void delete(Account account, Long postId){
        Post post = postRepository.findById(postId)
                .orElseThrow(()->new IllegalArgumentException("[게시글:"+postId+"] 해당 게시글이 없습니다."));
        postRepository.delete(post);
    }

    public List<Post> getPostList(Pageable pageable) {
        int page = pageable.getPageNumber() == 0 ? 0 : pageable.getPageNumber()-1;
        pageable = PageRequest.of(page, 10);
        return postRepository.findAll(pageable).toList();
    }

    //좋아요

    @Transactional
    public Long like(Long postId, Account account){
        Post post = postRepository.findById(postId)
                .orElseThrow(()->new IllegalArgumentException("["+postId+"] 해당 게시글이 없습니다."));

        PostLike like = PostLike.builder().post(post).account(account).build();
        return postLikeRepository.save(like).getPost_like_id();
    }

    @Transactional
    public void dislike(Account account, Long postId){
        Post post = postRepository.findById(postId)
                .orElseThrow(()->new IllegalArgumentException("["+postId+"] 해당 게시글이 없습니다."));
        PostLike post_like = postLikeRepository.findByAccountAndPost(account, post);
        postLikeRepository.delete(post_like);
    }

    //댓글

    @Transactional
    public PostCommentResponseDto createPostComment(Account account, Long postId, PostCommentRequestDto requestDto){
        PostComment comment = requestDto.toPostComment();
        comment.setAccount(account);

        Post post = postRepository.findById(postId)
                .orElseThrow(()->new IllegalArgumentException("["+postId+"] 해당 게시글이 없습니다."));
        comment.setPost(post);

        //order num 설정

        return PostCommentResponseDto.of(postCommentRepository.save(comment));
    }

    @Transactional
    public PostCommentResponseDto readPostComment(Long post_comment_id){
        PostComment post_comment = postCommentRepository.findById(post_comment_id)
                .orElseThrow(()->new IllegalArgumentException("[댓글:"+post_comment_id+"] 해당 댓글이 없습니다."));
        return PostCommentResponseDto.of(post_comment);
    }

    public void deletePostComment(Long post_comment_id, Account account, Long postId){
        PostComment post_comment = postCommentRepository.findById(post_comment_id)
                .orElseThrow(()->new IllegalArgumentException("[댓글:"+post_comment_id+"] 해당 댓글이 없습니다."));
        //account 권한 확인
        postCommentRepository.delete(post_comment);
    }

}

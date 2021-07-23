package sungshin.sooon.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sungshin.sooon.dto.*;
import sungshin.sooon.model.Post;
import sungshin.sooon.model.Post_comment;
import sungshin.sooon.model.Post_like;
import sungshin.sooon.repository.PostCommentRepository;
import sungshin.sooon.repository.PostLikeRepository;
import sungshin.sooon.repository.PostRepository;

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
    public Long save(PostCreateRequestDto dto){
        Post post = dto.toPost();
        return postRepository.save(post).getPost_id();
    }

    @Transactional
    public Long update(Long post_id, PostUpdateRequestDto dto){
        Post post = postRepository.findById(post_id)
                .orElseThrow(()->new IllegalArgumentException("[게시글:"+post_id+"] 해당 게시글이 없습니다."));
        post.update(dto.getTitle(), dto.getContent(), dto.getIs_anonymous());
        return post_id;
    }

    @Transactional
    public PostResponseDto read(Long post_id){
        Post post = postRepository.findById(post_id)
                .orElseThrow(()->new IllegalArgumentException("[게시글:"+post_id+"] 해당 게시글이 없습니다."));

        PostResponseDto resultDto = new PostResponseDto(post);
        return resultDto;
    }

    @Transactional
    public void delete(Long post_id){
        Post post = postRepository.findById(post_id)
                .orElseThrow(()->new IllegalArgumentException("[게시글:"+post_id+"] 해당 게시글이 없습니다."));
        postRepository.delete(post);
    }

    public Page<Post> getPostList(Pageable pageable) {
        int page = pageable.getPageNumber() == 0 ? 0 : pageable.getPageNumber()-1;
        pageable = PageRequest.of(page, 10);

        return postRepository.findAll(pageable);
    }

    //좋아요

    @Transactional
    public Long like(PostLikeRequestDto dto){
        Post_like like = dto.to_Post_like();
        return postLikeRepository.save(like).getPost_like_id();
    }

    @Transactional
    public void dislike(Long post_like_id){
        Post_like like = postLikeRepository.findById(post_like_id)
                .orElseThrow(()->new IllegalArgumentException("[좋아요:"+post_like_id+"] 해당 좋아요 내역이 없습니다."));
        postLikeRepository.delete(like);
    }

    //댓글

    @Transactional
    public PostCommentResponseDto createPostComment(PostCommentCreateRequestDto dto){
        Post_comment post_comment = dto.toPost_comment();
        return new PostCommentResponseDto(postCommentRepository.save(post_comment));
    }

    @Transactional
    public PostCommentResponseDto readPostComment(Long post_comment_id){
        Post_comment post_comment = postCommentRepository.findById(post_comment_id)
                .orElseThrow(()->new IllegalArgumentException("[댓글:"+post_comment_id+"] 해당 댓글이 없습니다."));
        return new PostCommentResponseDto(post_comment);
    }

    public void deletePostComment(Long post_comment_id){
        Post_comment post_comment = postCommentRepository.findById(post_comment_id)
                .orElseThrow(()->new IllegalArgumentException("[댓글:"+post_comment_id+"] 해당 댓글이 없습니다."));
        postCommentRepository.delete(post_comment);
    }

}

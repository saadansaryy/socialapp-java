package com.mecaps.socialApp.serviceImplementation;

import com.mecaps.socialApp.entity.Comment;
import com.mecaps.socialApp.entity.Post;
import com.mecaps.socialApp.entity.User;
import com.mecaps.socialApp.repository.CommentRepository;
import com.mecaps.socialApp.repository.PostRepository;
import com.mecaps.socialApp.repository.UserRepository;
import com.mecaps.socialApp.request.CommentRequest;
import com.mecaps.socialApp.response.CommentResponse;
import com.mecaps.socialApp.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    final private CommentRepository commentRepository;
    final private UserRepository userRepository;
    final private PostRepository postRepository;

    public CommentServiceImpl(CommentRepository commentRepository, UserRepository userRepository, PostRepository postRepository){
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    public List<CommentResponse> getAllComments() {
        List<Comment> commentList = commentRepository.findAll();
        return commentList.stream().map(CommentResponse::new).toList();
    }

    public CommentResponse getCommentById(Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new RuntimeException("Id not found"));
        return new CommentResponse(comment);
    }

    public CommentResponse createComment(CommentRequest commentRequest) {
        User user = userRepository.findById(commentRequest.getAuthor()).orElseThrow(() -> new RuntimeException("User Id not found"));
        Post post = postRepository.findById(commentRequest.getPostId()).orElseThrow(() -> new RuntimeException("Post Id not found"));

        Comment comment = new Comment();

        comment.setCommentString(commentRequest.getCommentString());
        comment.setAuthor(user);
        comment.setPostId(post);

        Comment save = commentRepository.save(comment);

        return new CommentResponse(save);
    }

    public CommentResponse updateComment(CommentRequest commentRequest, Long id) {
        User user = userRepository.findById(commentRequest.getAuthor()).orElseThrow(() -> new RuntimeException("User Id not found"));
        Post post = postRepository.findById(commentRequest.getPostId()).orElseThrow(() -> new RuntimeException("Post Id not found"));

        Comment comment = commentRepository.findById(id).orElseThrow(() -> new RuntimeException("Id not found"));

        comment.setCommentString(commentRequest.getCommentString());
        comment.setAuthor(user);
        comment.setPostId(post);

        Comment save = commentRepository.save(comment);

        return new CommentResponse(save);
    }


    public String deleteComment(Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new RuntimeException("Id not found"));

        commentRepository.delete(comment);

        return "Comment with id : " + id + " deleted successfully";
    }
}

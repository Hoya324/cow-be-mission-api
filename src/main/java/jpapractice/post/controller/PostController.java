package jpapractice.post.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jpapractice.post.controller.dto.response.PostAllResponse;
import jpapractice.post.controller.dto.response.PostResponse;
import jpapractice.post.entity.Post;
import jpapractice.post.controller.dto.request.PostRequest;
import jpapractice.post.service.PostService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("members/{memberId}/posts")
@RequiredArgsConstructor
public class PostController {

	private final PostService postService;

	@PostMapping("/new")
	public PostResponse post(@PathVariable Long memberId, @Valid @RequestBody PostRequest postRequest) {
		return postService.register(postRequest, memberId);
	}

	@GetMapping("/{postId}")
	public PostResponse findPost(@PathVariable Long postId) {
		Post post = postService.findPost(postId);
		return PostResponse.of(post);
	}

	@GetMapping
	public List<PostAllResponse> postList(@PathVariable Long memberId) {
		List<Post> posts = postService.findAllPosts(memberId);
		return posts.stream()
			.map(PostAllResponse::of)
			.collect(Collectors.toList());
	}

	@DeleteMapping("/{postId}")
	public void deletePost(@PathVariable Long postId, @PathVariable Long memberId) throws Exception {
		postService.delete(memberId, postId);
	}
}

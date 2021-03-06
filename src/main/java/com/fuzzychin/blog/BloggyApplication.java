package com.fuzzychin.blog;

import com.fuzzychin.blog.bean.Comment;
import com.fuzzychin.blog.bean.Post;
import com.fuzzychin.blog.bean.Tag;
import com.fuzzychin.blog.bean.User;
import com.fuzzychin.blog.repository.CommentRepository;
import com.fuzzychin.blog.repository.PostRepository;
import com.fuzzychin.blog.repository.TagRepository;
import com.fuzzychin.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class BloggyApplication {

	public static void main(String[] args) {
		SpringApplication.run(BloggyApplication.class, args);
	}


}

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
class SeedUser implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {
		for (int i=0; i<10; i++) {
			User user = new User();
			user.setUserName(String.format("%d%s", i,"FuzzyChinSeed"));
			user.setFirstName(String.format("%d%s", i, "Fuzzy"));
			user.setLastName(String.format("%d%s", i, "Fuzzy"));
			user.setEmailAddress(String.format("%d%s", i, "FuzzyChin@gmail.com"));
			userRepository.save(user);
		}

		for (int i=0; i<10; i++) {
			User user = new User();
			user.setUserName(String.format("%d%s", i,"PinkySeed"));
			user.setFirstName(String.format("%d%s", i, "Pinky"));
			user.setLastName(String.format("%d%s", i, "Brain"));
			user.setEmailAddress(String.format("%d%s", i, "PinkyAndTheBrain@gmail.com"));
			userRepository.save(user);
		}
	}
}

@Component
class SeedPosts implements CommandLineRunner{

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {
		for (int i=0; i<10; i++) {
			Post post = new Post();
			post.setBody(String.format("%d%s", i , "Seeded Fuzzy Post"));
			post.setTitle(String.format("%d%s", i, "Seeded Fuzzy Title"));
			post.setUser(userRepository.findOne((long) 1));
			postRepository.save(post);
		}

		for (int i=0; i<10; i++) {
			Post post = new Post();
			post.setBody(String.format("%d%s", i , "Seeded Pinky Post"));
			post.setTitle(String.format("%d%s", i, "Seeded Pinky Title"));
			post.setUser(userRepository.findOne((long) 2));
			postRepository.save(post);
		}
	}
}

@Component
class SeedTags implements CommandLineRunner {

	@Autowired
	private TagRepository tagRepository;

	@Override
	public void run(String... args) throws Exception {
		for (int i = 0; i < 10; i++) {
			Tag tag = new Tag();
			tag.setDescriptor(String.format("%d%s", i, "TagDescSeed"));
			tag.setContent(String.format("%d%s", i, "TagContentSeed"));
			tagRepository.save(tag);
		}
	}
}

@Component
class seedComments implements CommandLineRunner{

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CommentRepository commentRepository;

 	@Override
	public void run(String... args) throws Exception{
		for(int i=0; i<10; i++){
			Comment comment = new Comment(
					String.format("%d%s", i, "CommentSeed"),
					userRepository.findOne((long) 1),
					postRepository.findOne((long) 1)
			);
			commentRepository.save(comment);
		}
	}
}

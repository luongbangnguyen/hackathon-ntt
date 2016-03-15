package com.vn.ntt;

import com.vn.ntt.entity.Buddy;
import com.vn.ntt.entity.Hashtag;
import com.vn.ntt.repository.BuddyRepository;
import com.vn.ntt.repository.HashtagRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
public class Application{
	private static final Logger log = LoggerFactory.getLogger(Application.class);


	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner initHashtag(HashtagRepository hashtagRepository) throws Exception {

		return (args) -> {
			long count = hashtagRepository.count();
			if(count > 0){
				return;
			}
			List<Hashtag> hashtags = new ArrayList<>();
			URI fileName =  getClass().getClassLoader().getResource("hash.txt").toURI();
			try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
				stream.forEach(str -> {
					log.info(str);
					Hashtag hash = new Hashtag(str,"");
					hashtags.add(hash);
				});
				hashtagRepository.save(hashtags);
			} catch (Exception e) {
				e.printStackTrace();
			}
		};
	}

	@Bean
	public CommandLineRunner initBuddy(BuddyRepository buddyRepository)throws Exception{
		return (args -> {
			if(buddyRepository.count() > 0){
				return;
			}
			List<Buddy> buddies = new ArrayList<>();
			for(int i = 0; i < 100; i++){
				List<Hashtag> hashtags = new ArrayList<>();
				hashtags.add(new Hashtag("tired",""));
				hashtags.add(new Hashtag("home",""));
				hashtags.add(new Hashtag("usa",""));
				Buddy buddy = new Buddy();
				buddy.setName("NinHN");
				buddy.setHashtags(hashtags);
				buddies.add(buddy);
			}
			buddyRepository.save(buddies);
		});

	}
}


package com.lambton.project;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.context.request.RequestContextListener;

import com.lambton.project.entity.Customer;
import com.lambton.project.entity.Item;
import com.lambton.project.repository.CustomerRepository;
import com.lambton.project.repository.ItemRepository;

@SpringBootApplication
public class StartApplication extends SpringBootServletInitializer {
	private static final Logger logger = LoggerFactory.getLogger(StartApplication.class);
	private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(StartApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(StartApplication.class, args);
	}

	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer() {
		return new EmbeddedServletContainerCustomizer() {
			@Override
			public void customize(ConfigurableEmbeddedServletContainer container) {
				container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/404.html"));
			}
		};
	}

	@Bean
	public RequestContextListener requestContextListener() {
		return new RequestContextListener();
	}

	@Bean
	public CommandLineRunner setup(ItemRepository itemRepository, CustomerRepository customerRepository) {
		return (args) -> {
			itemRepository.save(new Item("FALAFEL",
					"Delicious fried chickpea balls paired with a blended veggie mix of local garlic, cilantro, and parsley. Lightly spiced, our infamous falafels are fluffy on the inside with a light golden crunch on the outside.",
					new BigDecimal(1.25), "//static//img//FALAFEL.jpg"));
			itemRepository.save(new Item("BEEF LIVER", "Pan-Fried beef liver with onions, garlic, and spices.",
					new BigDecimal(1.25), "//img//FALAFEL.jpg"));
			itemRepository.save(new Item("CHICKEN SHISH TAWOOK",
					"Delicately pan-seared chicken breast chunks topped with a delicious blend of spices then lightly grilled.",
					new BigDecimal(1.25), "//img//FALAFEL.jpg"));
			itemRepository.save(new Item("TILAPIA FISH FILLET", "Seasoned Tilapia fish filet grilled to perfection.",
					new BigDecimal(1.25), "//img//FALAFEL.jpg"));
			itemRepository.save(new Item("CHICKEN SHAWARMA",
					"Scrumptious shredded grilled chicken meat marinated with special Lebanese spices for the perfect Lebanese experience.",
					new BigDecimal(1.25), "//img//FALAFEL.jpg"));
			itemRepository.save(new Item("KIDNEY BEANS", "Fresh stew of kidney beans in a savoury tomato and garlic",
					new BigDecimal(1.25), "//img//FALAFEL.jpg"));
			itemRepository.save(new Item("CAULIFLOWER STEW",
					"Cauliflower and zucchini chunks in a flavourful split and tomato gravy", new BigDecimal(1.25),
					"//img//FALAFEL.jpg"));
			itemRepository.save(new Item("IJJA (VEGGIE PATTY)",
					"Veggie ball medley of shredded zucchini, cauliflower bits, potatoes, fresh parsley leaves & onions.",
					new BigDecimal(1.25), "//img//FALAFEL.jpg"));
			itemRepository.save(new Item("BEEF SHAWARMA", "Tender juicy beef shawarma strips.", new BigDecimal(1.25),
					"//img//FALAFEL.jpg"));
			itemRepository.save(new Item("CHICKEN SHAWARMA",
					"Scrumptious shredded grilled chicken meat marinated with special Lebanese spices for the perfect Lebanese experience.",
					new BigDecimal(1.25), "//img//FALAFEL.jpg"));
			itemRepository.save(new Item("GREEN LENTIL SALAD",
					"Steamed green lentils with a tangy medley of diced lemon, celery, cucumber, and garlic bits.",
					new BigDecimal(1.25), "//img//FALAFEL.jpg"));
			itemRepository.save(new Item("BEET SALAD",
					"Perfect blend of julienned sweet beets & pickled turnips topped with a light sumac dressing.",
					new BigDecimal(1.25), "//img//FALAFEL.jpg"));
			itemRepository.save(new Item("CHICKEN RICE", "Delicious chicken rice with potatoes and turmeric.",
					new BigDecimal(1.25), "//img//FALAFEL.jpg"));
			itemRepository.save(
					new Item("BEEF SAMOSA", "Crispy samosa layer stuffed with mildly-spiced beef and diced onions.",
							new BigDecimal(1.25), "//img//FALAFEL.jpg"));
			itemRepository.save(new Item("RED LENTIL SOUP",
					"Hearty thick red lentil soup with onions, spices, with a hint of cumin", new BigDecimal(1.25),
					"//img//FALAFEL.jpg"));
			itemRepository.save(
					new Item("STUFFED PEPPERS", "Stuffed whole pepper with a rice, chickpea, veggie and herb mixture.",
							new BigDecimal(1.25), "//img//FALAFEL.jpg"));
			itemRepository.save(new Item("GRAPE LEAVES",
					"Finger-licking lemony home-made grape leaves stuffed with a delicious medley of rice, chick pea bits, onions, and diced parsley.",
					new BigDecimal(1.25), "//img//FALAFEL.jpg"));
			itemRepository.save(new Item("HUMMOS", "Wonderful garlicky chickpea puree with a hint of lemon and tahini",
					new BigDecimal(1.25), "//img//FALAFEL.jpg"));
			itemRepository.save(new Item("BARAZE", "Crispy sesame seed cookie with pistachios.", new BigDecimal(1.25),
					"//img//FALAFEL.jpg"));
			itemRepository.save(new Item("BAKLAVA",
					"Scrumptious Lebanese dessert of layered flaky filo pastry with honey and your choice of nut of almonds, cashews or pistachios.",
					new BigDecimal(1.25), "//img//FALAFEL.jpg"));

			logger.info("The sample data has been generated");
			Customer admin = new Customer();
			admin.setUsername("lambton");

			admin.setPassword(bCryptPasswordEncoder.encode("admin"));
			customerRepository.save(admin);
		};
	}
}

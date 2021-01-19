package fr.training.spring.library;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;
import java.util.Arrays;
import java.util.Optional;

import fr.training.spring.library.domain.*;
import fr.training.spring.library.infrastructure.BibliothequeDAO;
import fr.training.spring.library.infrastructure.entity.BibliothequeEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("tp-spring-0")
class BibliothequeApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private BibliothequeDAO bibliothequeDAO;

	public static final Livre SPRING_IN_ACTION = new Livre(null, "1617294942", "Spring in Action", "Craig Walls", 310,
			Genre.AMOUR);

	public static final Bibliotheque NATIONAL_Bibliotheque_MONTREUIL = new Bibliotheque(0L, TypeDeBibliotheque.NATIONALE,
			new Adresse(93, "Rue des Montreuil", 93100, "Montreuil"), new Directeur("NOEL", "Romain"));
	public static final Bibliotheque NATIONAL_LIBRARY_MONTREUIL2 = new Bibliotheque(0L, TypeDeBibliotheque.SCOLAIRE,
			new Adresse(3, "Rue de Paris1", 75001, "Paris1"), new Directeur("Romain", "NOEL"));
	public static final Bibliotheque SCHOOL_Bibliotheque_PARIS = new Bibliotheque(0L, TypeDeBibliotheque.SCOLAIRE,
			new Adresse(75, "Rue de Paris", 75008, "Paris"), new Directeur("LECHAT", "Garfield"));
	public static final Bibliotheque SCHOOL_Bibliotheque_PARIS2 = new Bibliotheque(0L, TypeDeBibliotheque.SCOLAIRE,
			new Adresse(75, "Rue de Paris", 75008, "Paris"), new Directeur("=LOUISE", "Nicolas"));
	public static final Bibliotheque PUBLIC_Bibliotheque_VINCENNES = new Bibliotheque(0L, TypeDeBibliotheque.PUBLIQUE,
			new Adresse(94, "Rue de Vincennes", 94200, "Vincennes"), new Directeur("LECHAT", "Garfield"));
	public static final Bibliotheque DUMMY_LIBRARY = new Bibliotheque(null, null, new Adresse(0, "DUMMY_STREET", 0, "DUMMY_CITY"),
			new Directeur("DUMMY_NAME", "DUMMY_SURNAME"), Arrays.asList(SPRING_IN_ACTION));

	public static final BibliothequeEntity NATIONAL_LIBRARY_MONTREUIL_JPA = new BibliothequeEntity(NATIONAL_Bibliotheque_MONTREUIL);
	public static final BibliothequeEntity SCHOOL_LIBRARY_PARIS_JPA = new BibliothequeEntity(SCHOOL_Bibliotheque_PARIS);
	public static final BibliothequeEntity NATIONAL_LIBRARY_MONTREUIL2_JPA = new BibliothequeEntity(NATIONAL_LIBRARY_MONTREUIL2);
	public static final BibliothequeEntity SCHOOL_LIBRARY_PARIS2_JPA = new BibliothequeEntity(SCHOOL_Bibliotheque_PARIS2);
	public static final BibliothequeEntity PUBLIC_LIBRARY_VINCENNES_JPA = new BibliothequeEntity(PUBLIC_Bibliotheque_VINCENNES);
	public static final BibliothequeEntity DUMMY_LIBRARY_JPA = new BibliothequeEntity(DUMMY_LIBRARY);

	// As long as we have some other integration tests, this is useless
	// @Test
	// public void contextLoads() {
	//
	// }

	@BeforeEach
	public void reset() {
		bibliothequeDAO.deleteAll();
		setup();
	}

	private void setup() {
		bibliothequeDAO.save(NATIONAL_LIBRARY_MONTREUIL_JPA);
		bibliothequeDAO.save(SCHOOL_LIBRARY_PARIS_JPA);
		bibliothequeDAO.save(NATIONAL_LIBRARY_MONTREUIL2_JPA);
		bibliothequeDAO.save(SCHOOL_LIBRARY_PARIS2_JPA);
		bibliothequeDAO.save(PUBLIC_LIBRARY_VINCENNES_JPA);
		bibliothequeDAO.save(DUMMY_LIBRARY_JPA);

	}

	@Nested
	@DisplayName("Api GET:/libraries ")
	class TestReadAll {
		@Test
		@DisplayName("should return empty list when no libraries created beforehand")
		void test_read_all_1() {
			// --------------- Given ---------------
			// The DB has been reset (see method annotated with @BeforeEach)

			// --------------- When ---------------
			// I do a request on /libraries
			final ResponseEntity<Bibliotheque[]> response = restTemplate.getForEntity("/bibliotheques", Bibliotheque[].class);

			// --------------- Then ---------------
			// I get an empty list and a response code 200
			assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
			assertThat(response.getBody()).isEmpty();
		}

		@Test
		@DisplayName("should return one Bibliotheque when 1 Bibliotheque was created beforehand")
		void test_read_all_2() {
			// --------------- Given ---------------
			// The DB has been reset (see method annotated with @BeforeEach)
			bibliothequeDAO.save(NATIONAL_LIBRARY_MONTREUIL_JPA);

			// --------------- When ---------------
			// I do a request on /bibliotheques
			final ResponseEntity<Bibliotheque[]> response = restTemplate.getForEntity("/bibliotheques", Bibliotheque[].class);

			// --------------- Then ---------------
			// I get an empty list and a response code 200
			assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
			assertThat(response.getBody()).hasSize(1);
			// TODO : Check equality
		}
	}

	@Test
	@DisplayName("Api POST:/bibliotheques should return a status created with ID of created Bibliotheque when passing a correct Bibliotheque")
	void test_create_1() {
		// --------------- Given ---------------
		// The DB has been reset (see method annotated with @BeforeEach)

		// --------------- When ---------------
		// I do a request on /bibliotheques
		final ResponseEntity<URI> response = restTemplate.postForEntity("/bibliotheque", NATIONAL_Bibliotheque_MONTREUIL,
				URI.class);

		// --------------- Then ---------------
		// I get a success code, and a new Bibliotheque in the database with the given ID
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		assertThat(response.toString()).contains("107");

/*		final Optional<Bibliotheque> BibliothequeFromDB = bibliothequeDAO.findById(idCreated);
		assertThat(BibliothequeFromDB).isNotEmpty();*//*

		// Due to equals method not being implemented, we would need to compare field by
		// fields...which is bad !
		// We'll talk about equality in DDD further in this course.
		// TODO : Check equality
		assertThat(BibliothequeFromDB.get().getType()).isEqualTo(NATIONAL_Bibliotheque_MONTREUIL.getType());*/
	}

	@Nested
	@DisplayName("Api PUT:/bibliotheque")
	class Test_update {
		@Test
		@DisplayName(" should update the Bibliotheque when passing on a correct ID")
		void test_update_1() {
			// --------------- Given ---------------
			Bibliotheque bibliothequeModifiee=NATIONAL_Bibliotheque_MONTREUIL;
			bibliothequeModifiee.setType(TypeDeBibliotheque.SCOLAIRE);
			final Long idOfSavedBibliotheque = bibliothequeModifiee.getId();

			// --------------- When ---------------
			restTemplate.put("/bibliotheque/", bibliothequeModifiee);

			// --------------- Then ---------------
			final Optional<BibliothequeEntity> BibliothequeFromDB = bibliothequeDAO.findById(idOfSavedBibliotheque);
			assertThat(BibliothequeFromDB).isNotEmpty();

			// TODO : Check equality
			assertThat(BibliothequeFromDB.get().getType()).isEqualTo(SCHOOL_Bibliotheque_PARIS.getType());
		}

		@Test
		@DisplayName(" should send an error when passing on an incorrect ID")
		void test_update_2() {
			// --------------- Given ---------------
			final Long idOfSavedBibliotheque = NATIONAL_Bibliotheque_MONTREUIL.getId();

			// --------------- When ---------------
			final ResponseEntity<String> response = restTemplate.exchange("/bibliotheques/" + Long.MAX_VALUE,
					HttpMethod.PUT, new HttpEntity<>(SCHOOL_Bibliotheque_PARIS), String.class);

			// --------------- Then ---------------
			assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
			assertThat(response.getBody()).contains("Not Found");
		}
	}

	@Nested
	@DisplayName("Api DELETE:/bibliotheque")
	class Test_delete {
		@Test
		@DisplayName(" should delete the Bibliotheque when passing on a correct ID")
		void test_delete_1() {
			// --------------- Given ---------------
			final Long idOfSavedBibliotheque = NATIONAL_Bibliotheque_MONTREUIL.getId();

			// --------------- When ---------------
			restTemplate.delete("/bibliotheque/" + idOfSavedBibliotheque);

			// --------------- Then ---------------
			final Optional<BibliothequeEntity> BibliothequeFromDB = bibliothequeDAO.findById(idOfSavedBibliotheque);
			assertThat(BibliothequeFromDB).isEmpty();
		}

		@Test
		@DisplayName(" should send an error when passing on an incorrect ID")
		void test_delete_2() {
			// --------------- Given ---------------
			final Long idOfSavedBibliotheque = NATIONAL_Bibliotheque_MONTREUIL.getId();

			// --------------- When ---------------
			final ResponseEntity<String> response = restTemplate.exchange("/bibliotheques/" + Long.MAX_VALUE,
					HttpMethod.DELETE, null, String.class);

			// --------------- Then ---------------
			assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
			assertThat(response.getBody()).contains("Not Found");
		}
	}

	@Test
	@DisplayName("Api GET:/bibliotheques/type/{type} should return all NATIONAL bibliotheques when passing NATIONAL as parameter")
	void test_list_with_filter_1() {
		// --------------- Given ---------------
		//Rien a faire

		// --------------- When ---------------
		final ResponseEntity<Bibliotheque[]> response = restTemplate.getForEntity("/bibliotheques/type/" + TypeDeBibliotheque.SCOLAIRE,
				Bibliotheque[].class);

		// --------------- Then ---------------
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).hasSize(2).allMatch(Bibliotheque -> Bibliotheque.getType().equals(TypeDeBibliotheque.SCOLAIRE));
	}

	@Test
	@DisplayName("Api GET:/bibliotheques/director/{surname} should get all bibliotheques ruled by Garfield when passing Garfield as parameter")
	void test_list_with_filter_2() {
		// --------------- Given ---------------
		//Rien à faire

		// --------------- When ---------------
		final ResponseEntity<Bibliotheque[]> response = restTemplate
				.getForEntity("/bibliotheques/director/" + "LECHAT", Bibliotheque[].class);

		// --------------- Then ---------------
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).hasSize(2)
		.allMatch(Bibliotheque -> Bibliotheque.getDirecteur().getPrenom().equals("Garfield"));
	}

}
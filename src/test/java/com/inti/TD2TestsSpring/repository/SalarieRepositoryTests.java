package com.inti.TD2TestsSpring.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import javax.transaction.Transactional.TxType;

import com.inti.TD2TestsSpring.model.Salarie;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@DataJpaTest // = @SpringBootTest (mais ça récupère tout et nous on a juste besoin des
				// repository pour le moment que sur le JPA)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // ne prend pas en compte la base de
																				// données intégrées car nous on veut la
																				// database de mysql
public class SalarieRepositoryTests
{
	@Autowired
	SalarieRepository salarieRepository;

	Salarie s, s2, s3;

	@BeforeEach
	public void setUp()
	{
		s = new Salarie();
//		salarieRepository.save(s);
		s2 = new Salarie("Toto", "Titi", "tt@gmail.com");
		salarieRepository.save(s2);
//		s3 = new Salarie(10, "nS3", "pS3", "mS3@gmail.com");
//		salarieRepository.save(s3);
	}

	@Test
	public void testSaveSalarie()
	{
		// Given

		// When
		Salarie savedSalarie = salarieRepository.save(s2);

		// Then
		assertThat(savedSalarie).isNotNull();
		assertThat(savedSalarie.getId()).isGreaterThan(1);
	}

	@Test
	public void testFindByIDSalarie()
	{
		Salarie getSalarie = salarieRepository.getReferenceById(2);

		assertThat(getSalarie).isNotNull();
		assertThat(getSalarie.getId()).isEqualTo(2);
		assertThat(getSalarie.getNom()).isEqualTo("Sophie");
		assertThat(getSalarie.getPrenom()).isEqualTo("Marie");
		assertThat(getSalarie.getEmail()).isEqualTo("sm@gmail.com");
	}

	@Test
	public void testUpdateSalarie()
	{
		// Given
		Salarie s1 = salarieRepository.save(s2);

		// When
		s1.setNom("Ronaldo");
		s1.setPrenom("Cristiano");
		Salarie s2 = salarieRepository.save(s1);

		// Then
		assertThat(s2).isNotNull();
		assertThat(s2.getNom()).isEqualTo("Ronaldo");
	}
	
	@Test
	public void testFindAllSalarie()
	{
		//given
		Salarie s1 = salarieRepository.save(new Salarie("Jean", "Claude", "jc@gmail.com"));
		Salarie s2 = salarieRepository.save(new Salarie("Louis", "Claude", "lc@gmail.com"));
		
		//When
		List<Salarie> listeSalarie = salarieRepository.findAll();

		//Then
		assertThat(listeSalarie).isNotEmpty();
		assertThat(listeSalarie).hasSize(6);
		assertThat(listeSalarie.get(0).getClass()).hasSameClassAs(Salarie.class);
		assertThat(listeSalarie.get(4).toString()).hasToString(s1.toString());
		
//		assertThat(listeSalarie.get(0)).isEqualTo(salarieRepository.getReferenceById(1));
//		assertThat(listeSalarie.get(1)).isEqualTo(salarieRepository.getReferenceById(2));
//		assertThat(listeSalarie.get(2)).isEqualTo(salarieRepository.getReferenceById(3));
	}
	
	/*
	 ********************** Ne fonctionne pas 
	 */
	@Test
	public void testDeleteExceptionSalarie()
	{
		int id = 2;
//		Salarie salarie = salarieRepository.save(s2);
//		int id = salarieRepository.getReferenceById(salarie.getId()).getId();
//		
//		salarieRepository.delete(salarie);
//		
//		assertThat(salarieRepository.getReferenceById(id)).isNull();

		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
			salarieRepository.deleteById(id);
		});
	}

	@Test
	@javax.transaction.Transactional(value = TxType.REQUIRED)
	public void testDeleteSalarie()
	{
		int id = s2.getId();
		Salarie salarie = salarieRepository.getReferenceById(id);

		salarieRepository.delete(salarie);
		s2 = salarieRepository.getReferenceById(id);

		assertThat(salarie).isNull();
	}

}

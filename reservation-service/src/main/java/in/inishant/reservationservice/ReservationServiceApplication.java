package in.inishant.reservationservice;

import java.util.Collection;
import java.util.stream.Stream;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class ReservationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReservationServiceApplication.class, args);
	}
	

}

@Component
class SampleDataCLR implements CommandLineRunner{

	private final ReservationRepository reservationRepository;
	
	/**
	 * @param reservationRepository
	 */
	@Autowired
	public SampleDataCLR(ReservationRepository reservationRepository) {
		super();
		this.reservationRepository = reservationRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		
		Stream.of("Nishant","Josh","Long","Dave","Hero").forEach(name -> reservationRepository.save(new Reservation(name)));
		reservationRepository.findAll().forEach(System.out::println);
	}
	
}

interface ReservationRepository extends JpaRepository<Reservation, Long>{
	Collection<Reservation> findByReservationName(String rn);
}

@Entity
class Reservation {
	
	

	@Id
	@GeneratedValue
	private Long id;
	
	private String reservationName;
	
	
	/**
	 * 
	 */
	public Reservation() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param reservationName
	 */
	public Reservation(String reservationName) {
		this.reservationName = reservationName;
	}
	@Override
	public String toString() {
		return "Reservation [id=" + id + ", reservationName=" + reservationName + "]";
	}
	
	
}

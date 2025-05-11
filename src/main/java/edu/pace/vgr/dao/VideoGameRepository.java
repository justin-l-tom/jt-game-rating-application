package edu.pace.vgr.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.pace.vgr.entities.VideoGame;

public interface VideoGameRepository extends JpaRepository<VideoGame, Long> {

	@Override
	public Page<VideoGame> findAll(Pageable pageable);

	public VideoGame findByVideoGameId(long id);

	@Query(value = """
	        SELECT vg.*
	        FROM video_game vg
	        WHERE EXISTS (
	            SELECT 1
	            FROM unnest(vg.genres) AS g
	            WHERE LOWER(g) LIKE LOWER(:genre)
	        )
	    """, nativeQuery = true)
	public Page<VideoGame> findByGenres(String genre, Pageable pageable);
	
	@Query(value = "SELECT DISTINCT unnest(genres) FROM video_game", nativeQuery = true)
	public List<String> findAllGenresDistinct();

	@Query(value = """
	        SELECT vg.*
	        FROM video_game vg
	        WHERE EXISTS (
	            SELECT 1
	            FROM unnest(vg.platforms) AS g
	            WHERE LOWER(g) LIKE LOWER(:platform)
	        )
	    """, nativeQuery = true)
	public Page<VideoGame> findByPlatforms(String platform, Pageable pageable);
	
	@Query(value = """
	        SELECT vg.*
	        FROM video_game vg
	        WHERE EXISTS (
	            SELECT 1
	            FROM unnest(vg.genres) AS g
	            WHERE LOWER(g) LIKE LOWER(:genre)
	        )
	        AND EXISTS (
	            SELECT 1
	            FROM unnest(vg.platforms) AS g
	            WHERE LOWER(g) LIKE LOWER(:platform)
	        )
	    """, nativeQuery = true)
	public Page<VideoGame> findByGenresAndPlatforms(String genre, String platform, Pageable pageable);
	
}

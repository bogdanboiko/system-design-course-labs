package fotius.example.musicapp.presentation.rest;

import fotius.example.musicapp.domain.PlaylistService;
import fotius.example.musicapp.domain.model.Playlist;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/playlists")
@RequiredArgsConstructor
public class PlaylistController {
    private final PlaylistService playlistService;

    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public Playlist createPlaylist(@RequestParam("name") String name, @RequestParam("author") String author) {
        return playlistService
                .create(PlaylistService
                        .CreatePlaylistCommand
                        .builder()
                        .author(author)
                        .name(name)
                        .build());
    }

    @RequestMapping(
            method = RequestMethod.POST,
            path = "{id}/songs/{songId}"
    )
    @ResponseBody
    public Playlist addSongToPlaylist(@PathVariable("id") Long id, @PathVariable("songId") Long songId) {
        return playlistService.addSong(id, songId);
    }

    @RequestMapping(
            method = RequestMethod.DELETE,
            path = "{id}/songs/{songId}"
    )
    @ResponseBody
    public Playlist deleteSongFromPlaylist(@PathVariable("id") Long id, @PathVariable("songId") Long songId) {
        return playlistService.deleteSong(id, songId);
    }

    @RequestMapping(
            method = RequestMethod.DELETE,
            path = "{id}"
    )
    @ResponseBody
    public void deletePlaylist(@PathVariable("id") Long id) {
        playlistService.delete(id);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "{id}"
    )
    @ResponseBody
    public Playlist getAllSongsFromPlaylist(@PathVariable("id") Long id) {
        return playlistService.getById(id);
    }

}
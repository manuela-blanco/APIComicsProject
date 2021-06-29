package br.com.zup.comics.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.zup.comics.model.Comic;

@FeignClient(value = "MarvelAPI", url = "http://gateway.marvel.com/v1/public/comics/")
public interface MarvelAPIClient {
	
    @RequestMapping(method = RequestMethod.GET, value = "{comicId}?ts={timestamp}&apikey={publicKey}&hash={hash}",
   		 produces = "application/json")
    Comic getComicById(@PathVariable("comicId") Long comicId, @PathVariable("timestamp") String timestamp,
   		@PathVariable("publicKey") String publicKey, @PathVariable("hash") String hash);
}

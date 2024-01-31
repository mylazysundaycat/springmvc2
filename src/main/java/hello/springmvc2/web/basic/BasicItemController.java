package hello.springmvc2.web.basic;


import hello.springmvc2.domain.item.Item;
import hello.springmvc2.domain.item.ItemRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor //아래의 생성자를 만들어줌 (의존관계자동주입)
public class BasicItemController {
    private final ItemRepository itemRepository;

    /*
    //@Autowired //생성자가 하나면 Autowired를 생략할 수 있다.
    public BasicItemController(ItemRepository itemRepository){
        this.itemRepository=itemRepository;
    }
     */
    
    @GetMapping
    public String items(Model model){
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items",items);
        return "basic/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable("itemId") Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }

    /**
     * 테스트용 데이터
     */
    @PostConstruct
    public void init(){
        itemRepository.save(new Item("itemA", 10000,10));
        itemRepository.save(new Item("itemB", 20000, 20));
        
    }
}

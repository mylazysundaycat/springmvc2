package hello.springmvc2.web.basic;


import hello.springmvc2.domain.item.Item;
import hello.springmvc2.domain.item.ItemRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    //상품 등록 폼으로 이동
    @GetMapping("/add")
    public String addForm(){
        return "basic/addForm";
    }

    //상품 등록
    //@PostMapping("/add")
    public String addItemV1(@RequestParam("itemName") String itemName,
                       @RequestParam("price") int price,
                       @RequestParam("quantity") Integer quantity,
                       Model model) {
        Item item = new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);

        itemRepository.save(item);
        model.addAttribute("item", item);

        return "basic/item";
    }

    //@PostMapping("/add")
    public String addItemV2(@ModelAttribute("item") Item item,
                            Model model) {

        //@ModelAttribute 가 자동으로 만들어주는 부분
        /*
        Item item = new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);
         */

        itemRepository.save(item);
        //자동으로 모델에 담아줌
        //model.addAttribute("item", item);

        return "basic/item";
    }

    //@PostMapping("/add")
    public String addItemV3(@ModelAttribute("item") Item item) {
        itemRepository.save(item);
        return "basic/item";
    }

    //상품 상세화면으로 돌아갔을 때 새로고침시 상품 등록이 중복을 되는 문제 발생
    //@PostMapping("/add")
    public String addItemV4(Item item) { //객체로 오는 경우 ModelAttribute 생략
        itemRepository.save(item);
        return "basic/item";
    }

    @PostMapping("/add")
    public String addItemV5(Item item) { //객체로 오는 경우 ModelAttribute 생략
        itemRepository.save(item);
        return "redirect:/basic/items/"+item.getItemName();
    }

    //수정 폼
    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable("itemId") Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/editForm";
    }

    //수정 완료
    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable("itemId") Long itemId, @ModelAttribute Item item) {
        itemRepository.update(itemId, item);
        return "redirect:/basic/items/{itemId}";
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

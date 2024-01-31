package hello.springmvc2.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {
    private static final Map<Long, Item> store = new HashMap<>(); //멀티쓰레드 환경에서는 좋지 않음
    //멀티쓰레드 환경에선 ConcurrentHashMap 사용할 것
    private static long sequence = 0L;
    //멀티 쓰레드 환경에서 Automic long 사용할 것

    public Item save(Item item){
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    public Item findById(Long id){
        return store.get(id);
    }

    public List<Item> findAll(){
        return new ArrayList<>(store.values()); //안전하게 한번 감쌈
    }

    public void update(Long itemId, Item updateParam){ //실제로는 ItemDTO를 만드는게 좋다.
        //왜냐하면 Item 객체에서 사용하지 않는 멤버변수가 있기 때문
        Item findItem = findById(itemId);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }

    public void clearStore(){
        store.clear();
    }
}

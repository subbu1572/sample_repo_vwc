package com.sb.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sb.exception.ResouceNotFoundException1;
import com.sb.model.Item1;
import com.sb.repository.ItemRepository;
import com.sb.service.ItemService;


@RequestMapping("/itemapi")
@RestController
public class ItemController {
	@Autowired
	ItemService itemservice;
	@Autowired
	ItemRepository ir;
	
	system.out.println("HAI SUBBA RAO")
	
		/*RQ_1543-CODE CHANGES -START */
		@PatchMapping("/item/{id}/{name}")
	public ResponseEntity<Item1> updateEmployeePartially(@PathVariable Long id, @PathVariable Integer cost) {
		try {
			Item1 item = ir.findById(id).get();
			item.setCost(cost);
			return new ResponseEntity<Item1>(ir.save(item), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
		/*RQ_1543-CODE CHANGES -END */


	@PostMapping("/create")
	public ResponseEntity<Item1> createItem(@Valid @RequestBody Item1 item) {
		try {
			Item1 t = itemservice.m1(item);
			return new ResponseEntity<>(t, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	/*RQ_1541-CODE CHANGES -START */
		@PatchMapping("/item/{id}/{name}")
	public ResponseEntity<Item1> updateEmployeePartially(@PathVariable Long id, @PathVariable Integer cost) {
		try {
			Item1 item = ir.findById(id).get();
			item.setCost(cost);
			return new ResponseEntity<Item1>(ir.save(item), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
		/*RQ_1541-CODE CHANGES -END */




	@GetMapping("/items/{no}")
	public ResponseEntity < Item1 > getItemById
	(@PathVariable(value = "no") Long itemno)
	throws ResouceNotFoundException1{
		Item1 item = ir.findById(itemno).orElseThrow(() -> new ResouceNotFoundException1("Your"
				+ " Entered Item Number is not available in Database,Could you please try with other Item Number :: " + itemno));
	    return ResponseEntity.ok().body(item);
}
	
	@GetMapping("/readAll")
	public ResponseEntity<List<Item1>> getAllItems(@RequestParam(required = false) String name) throws ResouceNotFoundException1{
		
			List<Item1> t = new ArrayList<Item1>();

			if (name == null)
				ir.findAll().forEach(t::add);
			else
				ir.findByNameContaining(name).forEach(t::add);

			if (t.isEmpty()) {
				throw new ResouceNotFoundException1("Item is notr available");
			}

			return new ResponseEntity<>(t, HttpStatus.OK);
		
	}
	@PatchMapping("/item/{id}/{cost}")
	public ResponseEntity<Item1> updateEmployeePartially(@PathVariable Long id, @PathVariable Integer cost) {
		try {
			Item1 item = ir.findById(id).get();
			item.setCost(cost);
			return new ResponseEntity<Item1>(ir.save(item), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	/*Rq-1542-code changes -start */
		@GetMapping("/items/{no}")
	public ResponseEntity < Item1 > getItemById
	(@PathVariable(value = "no") Long itemno)
	throws ResouceNotFoundException1{
		Item1 item = ir.findById(itemno).orElseThrow(() -> new ResouceNotFoundException1("Your"
				+ " Entered Item Number is not available in Database,Could you please try with other Item Number :: " + itemno));
	    return ResponseEntity.ok().body(item);
}
	/*Rq-1542-code changes -end */


	
	}

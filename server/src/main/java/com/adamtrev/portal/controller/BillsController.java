package com.adamtrev.portal.controller;

import com.adamtrev.portal.apimodel.BillDto;
import com.adamtrev.portal.data.BillsPojo;
import com.adamtrev.portal.mapper.UserMapper;
import com.adamtrev.portal.repository.BillsRepository;
import lombok.AllArgsConstructor;
import org.apache.http.HttpRequest;
import org.apache.http.client.methods.HttpHead;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor(onConstructor_ = @Autowired)
public class BillsController {
    private static final String PREFIX_V1 = "/api/v1/";
    private static final String BILLS_API = PREFIX_V1 + "bills";

    private BillsRepository billsRepository;
    private UserMapper billsMapper;

    @PostMapping(BILLS_API)
    public ResponseEntity<BillDto> createBill(@RequestBody final BillDto bill) {
        final BillsPojo pojo = billsRepository.createBill(billsMapper.toPojo(bill));

        return ResponseEntity
                .ok()
                .body(billsMapper.toDto(pojo));
    }

    @GetMapping(BILLS_API)
    public ResponseEntity<List<BillDto>> getBills() {
        final List<BillsPojo> pojo = billsRepository.getBills();

        if (pojo == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity
                .ok()
                .body(pojo.stream().map(billsMapper::toDto).collect(Collectors.toList()));
    }


    @GetMapping(BILLS_API + "/{company}")
    public ResponseEntity<List<BillDto>> getBillsForCompany(@PathVariable(value = "company") final String companyName) {
        final List<BillsPojo> pojo = billsRepository.getBillsForCompany(companyName);

        if (pojo == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity
                .ok()
                .body(pojo.stream().map(billsMapper::toDto).collect(Collectors.toList()));
    }

    @DeleteMapping(BILLS_API + "/{company}/{payableDate}")
    public ResponseEntity<BillsPojo> deleteBill(@PathVariable(value = "company") final String company,
                                                @PathVariable(value = "payableDate") final String payableDate) {
        final BillsPojo pojo = billsRepository.deleteBill(company, new DateTime(payableDate));
        if (pojo == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(pojo);
    }
}
